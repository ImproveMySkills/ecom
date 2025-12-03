package com.improvemyskills.ecom.dto;


public class ProductDto {
    private Long id;
    private String reference;
    private String name;

    public ProductDto() {
    }

    public ProductDto(Long id, String reference, String name) {
        this.id = id;
        this.reference = reference;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
