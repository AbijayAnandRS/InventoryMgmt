package com.example.productinventorymgmt.products.models.requests;

import lombok.Getter;

@Getter
public class UpdateProductRequest extends CreateProductRequest {
    private int id;

    UpdateProductRequest(int id , String name, Integer supplierId, Double price, Integer stockQty) {
        super(name, supplierId, price, stockQty);
        this.id = id;
    }
}
