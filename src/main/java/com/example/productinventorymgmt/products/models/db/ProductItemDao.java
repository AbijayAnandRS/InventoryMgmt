package com.example.productinventorymgmt.products.models.db;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductItemDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Integer id;

    @Column(name = "name")
    private  String name;

    @Column(name = "supplier_id")
    private   Integer supplierId;

    @Column(name = "price")
    private  Double price;

    @Column(name = "stock_qty")
    private   Integer stockQty;
}
