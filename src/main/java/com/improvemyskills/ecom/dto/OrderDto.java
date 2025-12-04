package com.improvemyskills.ecom.dto;

import java.util.List;

public class OrderDto {
    private Long customerId;
    private List<Long> productIdsList;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<Long> getProductIdsList() {
        return productIdsList;
    }

    public void setProductIdsList(List<Long> productIdsList) {
        this.productIdsList = productIdsList;
    }

    public OrderDto() {
    }

    public OrderDto(Long customerId, List<Long> productIdsList) {
        this.customerId = customerId;
        this.productIdsList = productIdsList;
    }
}
