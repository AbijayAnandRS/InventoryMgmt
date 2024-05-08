package com.example.productinventorymgmt.products.repository;

import com.example.productinventorymgmt.products.models.db.ProductImageDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductImageRepository extends JpaRepository<ProductImageDao, Integer> {
}
