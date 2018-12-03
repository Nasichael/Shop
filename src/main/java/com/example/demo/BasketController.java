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

    @GetMapping("basket/{id}/total")
    BigDecimal calculateTotalPriceByBasketId(@PathVariable("id") int id) {
        return basketRepository.calculateTotalPriceByBasketId(id);
    }

    @DeleteMapping("basket/remove/{product_id}")
    public void removeFromBasket(@PathVariable("product_id") int product_id) {
        basketRepository.removeProductFromBasket(product_id);
    }

    @PostMapping(value = "basket/{basket_id}/add/{product_id}")
    public void addProductToBasket(@PathVariable int basket_id, @PathVariable int product_id) {
        basketRepository.addProductToBasket(basket_id, product_id);
    }

  }

