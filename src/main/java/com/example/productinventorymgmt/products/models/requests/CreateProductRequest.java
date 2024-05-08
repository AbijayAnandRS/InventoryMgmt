package com.example.productinventorymgmt.products.models.requests;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CreateProductRequest {

    private final String name;

    private final Integer supplierId;

    private final Double price;

    private final Integer stockQty;
}
