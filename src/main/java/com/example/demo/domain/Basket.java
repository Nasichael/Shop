package com.example.demo.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Basket {

    private BigDecimal totalPrice = BigDecimal.valueOf(0);
    List<Product> listOfProducts = new ArrayList<>();


    public List<Product> addProductToBasket(Product newProduct) {
        listOfProducts.add(newProduct);
        return listOfProducts;
    }

    public List<Product> viewBasket() {
        return listOfProducts;
    }

    public BigDecimal calculateTotalPrice(List<Product> list) {
        for (Product product : list) {
            totalPrice = totalPrice.add(product.getPrice());
        }
        return totalPrice;
    }
}
