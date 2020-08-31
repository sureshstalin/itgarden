package com.itgarden.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "PRODUCT")
public class Product extends BaseObject{

    @Column(name = "PRODUCT_NAME")
    private String name;

    @Column(name = "PRODUCT_PRICE")
    private Double price;

    @Column(name = "PRODUCT_TAX")
    private Double tax;

    @Column(name = "PRODUCT_TYPE")
    private String type;

}
