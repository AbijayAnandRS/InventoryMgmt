package com.example.productinventorymgmt.products.service;

import com.example.productinventorymgmt.products.models.other.ProductServiceException;
import com.example.productinventorymgmt.products.models.requests.CreateProductRequest;
import com.example.productinventorymgmt.products.models.requests.UpdateProductRequest;
import com.example.productinventorymgmt.products.models.responses.ProductItemResponse;

import java.util.List;

public interface ProductService {

    void createProduct(CreateProductRequest request) throws ProductServiceException;

    void updateProduct(UpdateProductRequest request) throws ProductServiceException;

    void deleteProduct(int id) throws ProductServiceException;

    List<ProductItemResponse> getProductItems(Integer supplierId, Integer minPrice, Integer maxPrice) throws ProductServiceException;

    ProductItemResponse getProductItemById(int id) throws ProductServiceException;

    void incrementOrDecrementStock(int productId, int delta) throws ProductServiceException;
}
