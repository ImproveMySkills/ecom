package com.improvemyskills.ecom.services.impl;


import com.improvemyskills.ecom.dto.LoginRequest;
import com.improvemyskills.ecom.dto.RegisterRequest;
import com.improvemyskills.ecom.models.Customer;
import com.improvemyskills.ecom.repository.CustomerRepository;
import com.improvemyskills.ecom.security.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthService {

    private final CustomerRepository users;
    private final PasswordEncoder encoder;
    private final JwtService jwt;

    public AuthService(CustomerRepository users, PasswordEncoder encoder, JwtService jwt) {
        this.users = users; this.encoder = encoder; this.jwt = jwt;
    }

    public void register(RegisterRequest req) {
        if (users.existsByUsername(req.username()))
            throw new IllegalArgumentException("Username déjà pris");

        Customer u = new Customer();
        u.setUsername(req.username());
        u.setPassword(encoder.encode(req.password())); // hash BCrypt
        u.getRoles().add("ROLE_USER");
        users.save(u);
    }

    public Map<String, String> login(LoginRequest req) {
        Customer u = users.findByUsername(req.username())
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable"));

        if (!encoder.matches(req.password(), u.getPassword()))
            throw new BadCredentialsException("Mot de passe invalide");

        String access  = jwt.generateAccessToken(u.getUsername(), u.getRoles());
        String refresh = jwt.generateRefreshToken(u.getUsername());

        return Map.of("access", access, "refresh", refresh);
    }

    public String refresh(String refreshToken) {
        // Vérifier le refresh token
        Jws<Claims> jws = jwt.parse(refreshToken);
        String username = jws.getBody().getSubject();

        Customer u = users.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable"));

        // (Optionnel) : rotation / vérification jti en base pour éviter le reuse
        return jwt.generateAccessToken(u.getUsername(), u.getRoles());
    }
}
