package com.itgarden.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NotNull(message = "Product Name cannot be empty")
    @Column(name = "PRODUCT_NAME",nullable = false)
    private String productName;

    @NotNull(message = "Product price cannot be empty")
    @Column(name = "PRODUCT_PRICE",nullable = false)
    private Double productPrice;

    @NotNull(message = "Product stock count cannot be empty")
    @Column(name = "PRODUCT_STOCK_COUNT",nullable = false)
    private Integer productStockCount;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductStockCount() {
        return productStockCount;
    }

    public void setProductStockCount(Integer productStockCount) {
        this.productStockCount = productStockCount;
    }
}
