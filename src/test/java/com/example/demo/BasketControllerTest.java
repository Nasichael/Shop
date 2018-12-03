package com.example.demo;

import com.example.demo.domain.BasketRepository;
import com.example.demo.domain.Product;
import com.google.common.collect.Lists;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class BasketControllerTest {

    private static final int DUMMY_ID = 1;
    private static final String DUMMY_NAME = "dress";
    private static final BigDecimal DUMMY_PRICE = BigDecimal.valueOf(500);

    BasketRepository basketRepository;


    private List<Product> products() {
        return Lists.newArrayList(
                Product.builder()
                        .id(DUMMY_ID)
                        .name(DUMMY_NAME)
                        .price(DUMMY_PRICE)
                        .build(),
                Product.builder()
                        .id(2)
                        .name(DUMMY_NAME)
                        .price(DUMMY_PRICE)
                        .build()
        );
    }


    @BeforeEach
    void setUp() {
        basketRepository = mock(BasketRepository.class);

        RestAssuredMockMvc.standaloneSetup(
                MockMvcBuilders
                        .standaloneSetup(new BasketController(basketRepository))
        );
    }


    @Test
    void shouldCalculateTotalPriceByBasketId() {

        //given
        final int basket_id = 1;

        when(basketRepository.calculateTotalPriceByBasketId(basket_id)).thenReturn(DUMMY_PRICE);

        given()
                .when()
                .get("/basket/" + basket_id + "/total")
                //then
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("[0].price",is (DUMMY_PRICE));


        verify(basketRepository, times(1)).calculateTotalPriceByBasketId(basket_id);

    }



    @Test
    void shouldGetAllProductsByBasketId() {
        //given
        final int id = 1;
        when(basketRepository.getAllProductsByBasketId(id)).thenReturn(products());

        given()
                .when()
                .get("/basket/" + id)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("[0].id", is(DUMMY_ID))
                .body("[0].name", is(DUMMY_NAME))
                .body("[0].price", is (DUMMY_PRICE));

        verify(basketRepository, times(1)).getAllProductsByBasketId(id);
    }

    @Test
    void shouldDeleteProductById() {
        //given
        final int product_id = 0;

        given()
                .when()
                .delete("/basket/remove/" + product_id)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    void shouldAddProductToBasket(){
        //given
        final int product_id = 5;
        final int basket_id = 1;

        given()
                .when()
                .post("basket/"+ basket_id + "/add/" + product_id)
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK);

    }


}
