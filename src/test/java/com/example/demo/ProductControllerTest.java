package com.example.demo;

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

public class ProductControllerTest {

    private static final int DUMMY_ID = 4;
    private static final String DUMMY_NAME = "dress";
    private final BigDecimal DUMMY_PRICE = BigDecimal.valueOf(500);


    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);

        RestAssuredMockMvc.standaloneSetup(
                MockMvcBuilders
                        .standaloneSetup(new ProductController(productRepository))
        );
    }

    @Test
    void shouldReturnAllProducts() {

        System.out.println(DUMMY_PRICE);

        //given
        when(productRepository.getAll())
                .thenReturn(products());


        given()
                .when()
                .get("/items")
                //then
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("[0].id", is(DUMMY_ID))
                .body("[0].name", is(DUMMY_NAME))
                .body("[1].price", is(DUMMY_PRICE))
                .body("[1].id", is(99))
                .body("[1].name", is(DUMMY_NAME))
                .body("[1].price", is(DUMMY_PRICE));

        verify(productRepository, times(1)).getAll();
    }

    private List<Product> products() {
        return Lists.newArrayList(
                Product.builder()
                        .id(DUMMY_ID)
                        .name(DUMMY_NAME)
                        .price((DUMMY_PRICE))
                        .build(),
                Product.builder()
                        .id(99)
                        .name(DUMMY_NAME)
                        .price((DUMMY_PRICE))
                        .build()
        );
    }

    @Test
    void shouldSearchByKeyword() {
        //given

        final String keyword = "D";
        when(productRepository.search(keyword)).thenReturn(products());

        given()
                .when()
                .get("/items/search/" + keyword)
                //then
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .contentType(ContentType.JSON)
                .body("[0].id", is(DUMMY_ID))
                .body("[0].name", is(DUMMY_NAME))
                .body("[0].price",is(DUMMY_PRICE));

        verify(productRepository, times(1)).search(keyword);

    }
}