package com.example.productinventorymgmt.products.repository;

import com.example.productinventorymgmt.products.models.db.ProductItemDao;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.example.productinventorymgmt.products.service.ProductServiceImpl.*;

@Repository
public interface ProductRepository extends JpaRepository<ProductItemDao, Integer> {

    @Transactional
    @Modifying
    @Query(value = UPDATE_PRODUCT_ITEMS, nativeQuery = true)
    int updateProductItemById(@Param("id") int id, @Param("name") String name, @Param("supplierId") int supplierId,
                             @Param("price") Double price,  @Param("stockQty") int stockQty);

    @Modifying
    @Transactional
    @Query(value = UPDATE_STOCK_PRODUCT_ITEMS, nativeQuery = true)
    int updateStockByDelta(@Param("id") int id, @Param("delta") int delta);

    @Modifying
    @Transactional
    @Query(value = DELETE_FROM_PRODUCT_ITEM, nativeQuery = true)
    int deleteProductItemById(@Param("id") int id);
}
