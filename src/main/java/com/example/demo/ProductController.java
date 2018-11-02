package com.example.demo;


import com.example.demo.domain.Product;
import com.example.demo.domain.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class ProductController {


    private final Repository repository;

    public ProductController(Repository repository) {
        this.repository = repository;
    }

    @GetMapping("test")
    public String testRest() {
        String test = "testRest";
        System.out.println(test);
        return test;
    }

    /*@GetMapping ("item/{id}")
    Optional<Product> getById(int id) {
        return repository.getOne(id);
    }*/

}
