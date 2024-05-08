package com.example.productinventorymgmt.products.repository;

import com.example.productinventorymgmt.products.models.db.SupplierDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<SupplierDao, Integer> {
}
