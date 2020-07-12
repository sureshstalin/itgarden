package com.itgarden.model;

public class Product {

    private Long productId;

    private String productName;

    private Double productPrice;

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
