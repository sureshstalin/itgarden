package com.itgarden.repository;

import com.itgarden.entity.Product;

public interface ProductDAO {
    Product getProductById(Long ProductId);
}
