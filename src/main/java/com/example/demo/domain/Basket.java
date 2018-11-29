package com.example.demo.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Builder
@Data
@Getter


public class Basket {

    private int basketId;
    private Product product;

       /*public List<Product> viewBasket() {
        return listOfProducts;
    }

    public BigDecimal calculateTotalPrice() {
        for (Product product : listOfProducts) {
            totalPrice = totalPrice.add(product.getPrice());
        }
        return totalPrice;
        }*/

}

