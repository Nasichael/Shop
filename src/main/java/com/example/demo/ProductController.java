package com.example.demo;


import com.example.demo.domain.Product;
import com.example.demo.domain.Repository;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("items/search/{keyword}")
    List<Product> searchByKeyword(@PathVariable("keyword") String keyword) {
        return repository.search(keyword);
    }

    @GetMapping("items/{id}")
    Optional<Product> getById(@PathVariable("id") int id) {
        return repository.getOne(id);
    }

    @ApiOperation("Retrieves all items")
    @GetMapping("items")
    List<Product> getAll() {
        return repository.getAll();
    }

    }
