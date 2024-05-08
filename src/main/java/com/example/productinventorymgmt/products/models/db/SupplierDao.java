package com.example.productinventorymgmt.products.models.db;

import jakarta.persistence.*;

@Entity
@Table(name = "suppliers")
public class SupplierDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "contact", length = 45)
    private String contact;
}
