package com.example.demo;

import com.example.demo.domain.BasketRepository;
import com.example.demo.domain.Product;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
public class BasketController {

    private final BasketRepository basketRepository;

    public BasketController(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }


    @GetMapping("basket/{id}")
    List<Product> getAllProductsByBasketId(@PathVariable("id") int id) {
        return basketRepository.getAllProductsByBasketId(id);
    }

    /* @PostMapping("basket/1/add/{product_id}")
    public void addProductToBasket(@RequestBody Integer product_id) {
        basketRepository.addProductToBasket(1, product_id);
    }*/

    @GetMapping("basket/{id}/total")
    BigDecimal calculateTotalPriceByBasketId(@PathVariable("id") int id) {
        return basketRepository.calculateTotalPriceByBasketId(id);
    }
}
