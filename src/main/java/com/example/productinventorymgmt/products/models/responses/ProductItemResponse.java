package com.example.productinventorymgmt.products.models.responses;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class ProductItemResponse {

    private Integer id;

    private String name;

    private Integer supplierId;

    private Double price;

    private Integer stockQty;
}
