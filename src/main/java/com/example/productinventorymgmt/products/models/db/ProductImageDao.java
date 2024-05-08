package com.example.productinventorymgmt.products.models.db;

import jakarta.persistence.*;

@Entity
@Table(name = "product_images")
public class ProductImageDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "url", length = 500)
    private String url;

    @Column(name = "size")
    private Integer size;
}
