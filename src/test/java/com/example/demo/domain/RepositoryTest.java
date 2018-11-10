package com.example.demo.domain;

import org.assertj.core.data.Index;
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
import static org.junit.Assert.assertEquals;
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
//stworzyc produkty jacket i jumper
        //when
        List<Product> returnedProducts = repository.search(searchedLetter);

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

    private List<Product> createProducts() {
        List<Product> products = new ArrayList<>();
        Product product = Product.builder().build(); //zamiast konstruktora czyli new Product();

        //3, "dress", null, null, null, null, BigDecimal.ONE, "");
        products.add(product);
        return products;
    }

    @Test
    public void shouldNotFindProductContainingZ() {

        //given
        final String searchedLetter = "z";

        //when
        List<Product> returnedProducts = repository.search(searchedLetter);

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

        List<Product> list = repository.getAll();

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
        Optional<Product> item = repository.getOne(ID);

        //then
        assertThat(item).isPresent();
        assertThat(item.get()).isEqualTo(jumper);
    }

    @Test
    public void shouldFindNullWithId5() {

        //given
        final int ID = 5;

        //when
        Optional<Product> product = repository.getOne(ID);

        //then
        assertThat(product).isEmpty();

        // assertThat(product.equals(Optional.empty()));   //dlaczego to nie dziala???
        //assertEquals(Optional.empty(), product);
    }

    @Test
    public void shouldAddJumperToTheBasket() {

        //given
        Basket basket = new Basket();
        Product jumper = Product.builder()
                .id(2)
                .name("jumper")
                .build();

        Product dress = Product.builder()
                .id(0)
                .name("dress")
                .price(BigDecimal.valueOf(150))
                .build();

        //when
        basket.addProductToBasket(jumper);
        basket.addProductToBasket(dress);

        //then
        assertThat(basket.listOfProducts.size()).isEqualTo(2);
        assertThat(basket.listOfProducts.contains(jumper)).isTrue();

    }


    @Test
    public void shouldviewBasket() {

        //given
        Product jumper = Product.builder()
                .id(2)
                .name("jumper")
                .build();

        Basket basket = new Basket();
        basket.addProductToBasket(jumper);

        List<Product> listOfProducts = new ArrayList<>();
        listOfProducts.add(jumper);

        //when
        List<Product> listOfProducts2 = basket.viewBasket();

        //then
        assertThat(listOfProducts2).isEqualTo(listOfProducts);
    }

    @Test
    public void shouldCalculateTotalPrice() {

        //given
        Basket basket = new Basket();
        Product jumper = Product.builder()
                .id(2)
                .name("jumper")
                .price(BigDecimal.valueOf(300))
                .build();

        Product dress = Product.builder()
                .id(0)
                .name("dress")
                .price(BigDecimal.valueOf(150))
                .build();

        List<Product> listOfProducts = new ArrayList<>();
        listOfProducts.add(jumper);
        listOfProducts.add(jumper);
        listOfProducts.add(dress);

        //when
        BigDecimal totalPrice = basket.calculateTotalPrice(listOfProducts);

        //then
        assertThat(totalPrice).isEqualTo(BigDecimal.valueOf(750));
    }
}