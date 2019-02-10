package com.example.demo.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    ProductRepository productRepository;


    @Test
    public void checkIfGetOne() {

        //given
        int id = 0;
        String expectedName = "dress";

        //when
        final Optional<Product> returnedProduct = productRepository.getOne(id);

        //then
        assertThat(returnedProduct).isNotEmpty();
        assertThat(returnedProduct.get().getName()).isEqualTo(expectedName);
        assertThat(returnedProduct.get().getId()).isEqualTo(id);
    }

    @Test
    public void checkIfReturnEmptyIfProductNotExist() {

        //given
        int id = 5;

        //when
        final Optional<Product> returnedproduct = productRepository.getOne(id);

        //then
        assertThat(returnedproduct).isEmpty();
    }

    @Test
    public void shouldFindAllProductsContainingJ() {

        //given
        final String searchedLetter = "j";

        //when
        List<Product> returnedProducts = productRepository.search(searchedLetter);

        //then
        Product jumper = Product.builder()
                .name("jumper")
                .id(2)
                .build();


        Product jacket = Product.builder()
                .name("jacket")
                .id(1)
                .build();

        assertThat(returnedProducts).hasSize(2)
                .contains(jumper, jacket);
    }

   /* private List<Product> createProducts() {
        List<Product> products = new ArrayList<>();
        Product product = Product.builder().build(); //zamiast konstruktora czyli new Product();

        //3, "dress", null, null, null, null, BigDecimal.ONE, "");
        products.add(product);
        return products;
    }*/

    @Test
    public void shouldNotFindProductContainingZ() {

        //given
        final String searchedLetter = "z";

        //when
        List<Product> returnedProducts = productRepository.search(searchedLetter);

        //then
        assertThat(returnedProducts).isEmpty();
    }

    @Test
    public void testLombok() {
        Category.builder().build();
    }

    @Test
    public void shouldFindAllItems() {

        //given
        final int size = 3;

        //when
        List<Product> list = productRepository.getAll();

        //then
        assertThat(list.size()).isEqualTo(size);
    }

    @Test
    public void shouldFindById2() {

        //given
        final int ID = 2;
        Product jumper = Product.builder()
                .id(2)
                .name("jumper")
                .build();

        //when
        Optional<Product> item = productRepository.getOne(ID);

        //then
        assertThat(item).isPresent();
        assertThat(item.get()).isEqualTo(jumper);
    }

    @Test
    public void shouldFindNullWithId5() {

        //given
        final int ID = 5;

        //when
        Optional<Product> product = productRepository.getOne(ID);

        //then
        assertThat(product).isEmpty();
    }

    @Test
    public void shouldGetAllProductAvailableByProductId() {

        //given
        List<Integer> listOfProductsId = new ArrayList<>(Arrays.asList(3, 4));

        //when
        final List<Integer> allProductId = productRepository.getAllAvailableProdcutsByProductId();

        //then
        assertThat(allProductId.size()).isEqualTo(listOfProductsId.size());
        assertThat(allProductId).isEqualTo(listOfProductsId);
    }
}