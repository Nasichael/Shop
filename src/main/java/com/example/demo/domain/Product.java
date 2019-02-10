package com.example.demo.domain;

import lombok.*;

import java.math.BigDecimal;

@Builder
@Data    //pojemnik na dane - mozemy ustawiac (generuje settera)
//@Value  // ustawia gettery

public class Product {

    private int id;
    private String name;
    private Category category;
    private ClotheSize clotheSize;
    private BigDecimal price;
    private String url;

}
