package com.example.demo.domain;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    Repository repository;


    @Test
    public void checkIfGetOne() {
        //given
        int id = 0;

        //when
        final Optional<Product> returnedProduct = repository.getOne(id);

        //then
        assertThat(returnedProduct).isNotEmpty();

        String expectedName = "dress";
        assertThat(returnedProduct.get().getName()).isEqualTo(expectedName);
        assertThat(returnedProduct.get().getId()).isEqualTo(id);

    }

    @Test
    public void checkIfReturnEmptyIfProductNotExist() {

        //given
        int id = 5;
        //when
        final Optional<Product> returnedproduct = repository.getOne(id);
        //then
        assertThat(returnedproduct).isEmpty();
    }

    @Test
    public void shouldFindAllProductsContainingJ() {


        //given
        final String searchedLetter = "j";

        // dress jacket jumper belt
        // when(jdbcTemplate.queryForList("")).thenReturn(new ArrayList<>());
        List<Product> products = createProducts();
        when(jdbcTemplate.queryForList("", Product.class)).thenReturn(products);

        //when
        List<Product> returnedProducts = repository.search(searchedLetter);

        //then


    }

    private List<Product> createProducts() {
        List<Product> products = new ArrayList<>();
        Product prodcut = new Product();//3, "dress", null, null, null, null, BigDecimal.ONE, "");
        products.add(prodcut);
        return products;
    }

    public void shouldNotFindProductContainingC() {

    }

}