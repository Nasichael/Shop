package com.example.demo.domain;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

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
        final List<Product> products1 = jdbcTemplate.queryForList("select * from Product", Product.class);

        return new ArrayList<>();
    }

    /*try {
        //return jdbcTemplate.queryForObject("select * from Product where id=?", new Object[]{id},new ProductMapper());
        return jdbcTemplate.query("select * from Product where id=?", new ResultSetExtractor<Optional<Product>>() {

            @Override
            public Optional<Product> extractData(ResultSet resultSet) throws SQLException, DataAccessException {
                return Optional.empty();
            }
        })
    } catch () (EmptyResultDataAccessException e){
        return null;
    }*/


    public Optional<Product> getOne(int id) {


        return jdbcTemplate.queryForObject(
                "select * from Product where id=?",
                new Object[]{id},
                new ProductMapper());
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

}
