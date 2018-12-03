package com.example.demo.domain;

import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository

public class BasketRepository {

    JdbcTemplate jdbcTemplate;


    public BasketRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Product product(Map<String, Object> row) {
        return Product.builder()
                .id((Integer) row.get("id"))
                .name((String) row.get("name"))
                .price((BigDecimal) row.get("price"))
                .build();  //dlaczego tego nie widzi z ProductRepository i trzeba napisac drugi raz??
    }

    public void addProductToBasket(int basket_id, int product_id) {

        //dodac product_id tylko z listy istniejacych produktow...tak jak w sklepie online ze mozna dodac produkty dostepne

        String sqlInsert = "INSERT INTO basket VALUES (" + basket_id + "," + product_id + ")";
        jdbcTemplate.update(sqlInsert);
    }

       public void removeProductFromBasket(int product_id) {

        //to samo co w addProduct

        String sqlDelete = "DELETE FROM basket WHERE product_id = " + product_id;
        jdbcTemplate.update(sqlDelete);
    }

    public List<Product> getAllProductsByBasketId(int basket_id) {

        final List<Map<String, Object>> query = jdbcTemplate.queryForList
                ("SELECT basket_id, id ,name, price FROM product JOIN basket ON id = product_id WHERE basket.basket_id = " + basket_id);
        return query.stream()
                .map(row -> product(row))
                .collect(Collectors.toList());
    }

    public BigDecimal calculateTotalPriceByBasketId(int basket_id) {

        String sqlCalculatePrice = "SELECT SUM(price)\n" +
                "FROM product\n" +
                "JOIN basket ON id = product_id \n" +
                "WHERE basket_id = " + basket_id + "\n" +
                "GROUP BY basket_id";

        return jdbcTemplate.queryForObject(sqlCalculatePrice, BigDecimal.class);
    }
}
