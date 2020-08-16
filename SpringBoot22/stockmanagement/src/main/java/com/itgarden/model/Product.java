package com.itgarden.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel("This is some product request body information")
@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @ApiModelProperty(notes="Product Name can't be empty")
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
