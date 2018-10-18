package com.example.demo.domain;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class Repository {

    JdbcTemplate jdbcTemplate;

    public Repository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> search(String j) {
        //   final List<Map<String, Object>> maps = jdbcTemplate.queryForList("");

        // final List<Product> products1 = jdbcTemplate.queryForList("select * from Product", Product.class);
        //  final List<Product> products1 =
        jdbcTemplate.queryForRowSet("select * from Product");

        // final List<Product> query = jdbcTemplate.query("select * from Product", new ProductListMapper());
        // return new ArrayList<>();


         /*jdbcTemplate.query("select * from Product", new ResultSetExtractor<Optional<Product>>() {

            @Override
            public Optional<Product> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                return Optional.empty();
            }
        });*/

        return new ArrayList<>();
    }


    public Optional<Product> getOne(int id) {

        final SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("select * from Product where id=?", id);
        if (!sqlRowSet.next()) {
            return Optional.empty();
        }

        Product entity = new Product();
        entity.setId(sqlRowSet.getInt("id"));
        entity.setName(sqlRowSet.getString("name"));

        return Optional.of(entity);
    }

    class ProductMapper implements RowMapper<Optional<Product>> {

        @Override
        public Optional<Product> mapRow(ResultSet rs, int rowNum) throws SQLException {

            Product entity = new Product();
            entity.setId(rs.getInt("id"));
            entity.setName(rs.getString("name"));
            return Optional.of(entity);
        }


    }


    class ProductListMapper implements RowMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

            Product entity = new Product();
            entity.setId(rs.getInt("id"));
            entity.setName(rs.getString("name"));
            return entity;
        }


    }

}
