package com.example.demo.shop.products;

import com.example.demo.domain.Product;

import java.util.Optional;

public interface ProductsRepository {
    Optional<Product> find(int id);
}
