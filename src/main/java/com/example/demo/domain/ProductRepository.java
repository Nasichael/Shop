package com.example.demo.domain;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository
public class ProductRepository {

    JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Product product(Map<String, Object> row) {
        return Product.builder()
                .id((Integer) row.get("id"))
                .name((String) row.get("name"))
                .price((BigDecimal) row.get("price"))
                .build();
    }

    public List<Product> search(String keyWord) {
        final List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT id, name FROM product WHERE name LIKE '" + keyWord + "%'");

        return maps.stream()
                .map(row -> product(row))
                .collect(Collectors.toList());
    }

    public Optional<Product> getOne(int id) {
        final SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("select * from Product where id=?", id);

        if (!sqlRowSet.next()) {
            return Optional.empty();
        }

        final Product entity = Product.builder()
                .id(sqlRowSet.getInt("id"))
                .name(sqlRowSet.getString("name"))
                .build();
        return Optional.of(entity);
    }

    public List<Product> getAll() {
        final List<Map<String, Object>> query = jdbcTemplate.queryForList("SELECT * FROM Product");
        return query.stream()
                .map(row -> product(row))
                .collect(Collectors.toList());
    }

    /* class ProductListMapper implements RowMapper<Product> {*/

       /* @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

            return Product.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .build();
        }*/

    List<Integer> getAllAvailableProdcutsByProductId() {

        String sqlQueryForId = "SELECT id FROM product LEFT JOIN basket ON id = product_id WHERE basket_id is NULL ORDER BY id";
        return jdbcTemplate.queryForList(sqlQueryForId, Integer.class);
    }
}

