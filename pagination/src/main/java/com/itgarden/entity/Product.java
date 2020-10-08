package com.itgarden.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "productId")
    private Long productId;

    @Column(name = "PRODUCT_NAME")
    private String name;

    @Column(name = "PRODUCT_PRICE")
    private Double price;

    @Column(name = "PRODUCT_TAX")
    private Double tax;

    @Column(name = "PRODUCT_TYPE")
    private String type;

}
