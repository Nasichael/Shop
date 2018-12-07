package com.example.demo.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BasketRepositoryTest {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    BasketRepository basketRepository;

    @Test
    public void shouldGetAllProductsByBasketId() {

        //given
        final int basket_id = 1;
        final int listSize = 2;

        //when
        final List<Product> allProductsByBasketId = basketRepository.getAllProductsByBasketId(basket_id);

        //then
        assertThat(allProductsByBasketId.size()).isEqualTo(listSize);
    }

    @Test
    public void shouldAddProductToTheBasket() {

        //given
        final int product_id = 3;
        final int basket_id = 1;
        final int numberOfProducts = 3;

        //when
        basketRepository.addProductToBasket(basket_id, product_id);
        final List<Product> allProductsByBasketId = basketRepository.getAllProductsByBasketId(basket_id);

        //then
        assertThat(allProductsByBasketId.size()).isEqualTo(numberOfProducts);
    }


    @Test
    public void shouldDeleteProductFromBasket() {

        //given
        final int product_id = 3;
        final int basket_id = 1;
        final int numberOfProducts = 2;

        //when
        basketRepository.removeProductFromBasket(product_id);
        final List<Product> allProductsByBasketId = basketRepository.getAllProductsByBasketId(basket_id);

        //then
        assertThat(allProductsByBasketId.size()).isEqualTo(numberOfProducts);
    }


    @Test
    public void shouldCalculateTotalPriceByBasketId() {

        //given
        final int basket_id = 1;
        final BigDecimal TOTAL_PRICE = BigDecimal.valueOf(500);

        //when
        basketRepository.calculateTotalPriceByBasketId(basket_id);
        BigDecimal totalPrice = basketRepository.calculateTotalPriceByBasketId(basket_id);

        //then
        assertThat(totalPrice).isEqualTo(TOTAL_PRICE);
    }
}

