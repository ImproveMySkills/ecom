package com.improvemyskills.ecom.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Product> products;
}
