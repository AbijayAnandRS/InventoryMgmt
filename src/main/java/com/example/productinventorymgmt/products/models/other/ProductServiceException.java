package com.example.productinventorymgmt.products.models.other;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProductServiceException extends Exception {
    private String message;
    private String serviceInfo = "Product Service";
}
