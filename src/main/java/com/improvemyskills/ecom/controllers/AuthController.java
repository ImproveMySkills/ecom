package com.improvemyskills.ecom.controllers;


import com.improvemyskills.ecom.dto.AuthResponse;
import com.improvemyskills.ecom.dto.LoginRequest;
import com.improvemyskills.ecom.dto.RegisterRequest;
import com.improvemyskills.ecom.security.JwtService;
import com.improvemyskills.ecom.services.impl.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Map;

// AuthController.java
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService auth;
    private final JwtService jwt;

    public AuthController(AuthService auth, JwtService jwt) {
        this.auth = auth; this.jwt = jwt;
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequest req) {
        auth.register(req);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest req, HttpServletResponse res) {
        Map<String, String> tokens = auth.login(req);
        String access  = tokens.get("access");
        String refresh = tokens.get("refresh");

        // Placer le refresh token dans un cookie HttpOnly
        ResponseCookie cookie = ResponseCookie.from("refresh_token", refresh)
                .httpOnly(true).secure(true).sameSite("Strict")
                .path("/auth/refresh").maxAge(Duration.ofDays(7)).build();
        res.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok(new AuthResponse(access));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@CookieValue("refresh_token") String refreshToken,
                                                HttpServletResponse res) {
        String newAccess = auth.refresh(refreshToken);
        // (Optionnel) rotation du refresh token :
        String username = jwt.getUsername(refreshToken);
        String newRefresh = jwt.generateRefreshToken(username);
        ResponseCookie cookie = ResponseCookie.from("refresh_token", newRefresh)
                .httpOnly(true).secure(true).sameSite("Strict")
                .path("/auth/refresh").maxAge(Duration.ofDays(7)).build();
        res.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok(new AuthResponse(newAccess));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse res) {
        // Invalider le cookie côté client
        ResponseCookie cookie = ResponseCookie.from("refresh_token", "")
                .httpOnly(true).secure(true).sameSite("Strict")
                .path("/auth/refresh").maxAge(0).build();
        res.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return ResponseEntity.noContent().build();
    }
}
