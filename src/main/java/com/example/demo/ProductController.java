package com.example.demo;


import com.example.demo.domain.Product;
import com.example.demo.domain.ProductRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {


    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("test")
    public String testRest() {
        String test = "testRest";
        System.out.println(test);
        return test;
    }

    @GetMapping("items/search/{keyword}")
    List<Product> searchByKeyword(@PathVariable("keyword") String keyword) {
        return productRepository.search(keyword);
    }

    @GetMapping("items/{id}")
    Optional<Product> getById(@PathVariable("id") int id) {
        return productRepository.getOne(id);
    }

    @ApiOperation("Retrieves all items")
    @GetMapping("items")
    List<Product> getAll() {
        return productRepository.getAll();
    }
}
