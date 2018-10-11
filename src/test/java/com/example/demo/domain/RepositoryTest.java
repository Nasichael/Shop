package com.example.demo.domain;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RepositoryTest {

    @Mock
    private JdbcTemplate jdbcTemplate;


    Repository repository;

    @Before
    public void setup() {
     //   repository = new Repository(jdbcTemplate);
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
      ///  List<Product> returnedProducts = repository.search(searchedLetter);

        //then


    }

    private List<Product> createProducts() {
        List<Product> products = new ArrayList<>();
       // Product prodcut = new Product(3, "dress", null, null, null, null, BigDecimal.ONE, "");
        Product prodcut = new Product();
        products.add(prodcut);
        return products;
    }

    public void shouldNotFindProductContainingC() {

    }

}