package com.example.demo;

import com.example.demo.domain.BasketRepository;
import com.example.demo.domain.Product;
import com.example.demo.domain.ProductRepository;
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

    @BeforeEach
    void setUp() {
        basketRepository = mock(BasketRepository.class);

        RestAssuredMockMvc.standaloneSetup(
                MockMvcBuilders
                        .standaloneSetup(new BasketController(basketRepository))
        );
    }


   /* @Test
    void shouldCalculateTotalPriceByBasketId() {

        //given
        final int basket_id = 1;


        final BigDecimal DUMMY_PRICE = BigDecimal.valueOf(500);
        final BigDecimal totalPrice = DUMMY_PRICE;

        when(basketRepository.calculateTotalPriceByBasketId(basket_id)).thenReturn(totalPrice);

        given()
                .when()
                .get("/basket/" + basket_id + "/total")
                //then
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)



        verify(basketRepository, times(1).calculateTotalPriceByBasketId(basket_id));

    }*/

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
                .body("[0].name", is(DUMMY_NAME));

        verify(basketRepository, times(1)).getAllProductsByBasketId(id);
    }


}
