package com.example.demo.domain;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Repository {

    JdbcTemplate jdbcTemplate;

    public Repository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> search(String j) {
     //   final List<Map<String, Object>> maps = jdbcTemplate.queryForList("");
        final List<Product> products1 = jdbcTemplate.queryForList("", Product.class);

        return new ArrayList<>();
    }
}
