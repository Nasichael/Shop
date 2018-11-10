package com.example.demo.domain;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@org.springframework.stereotype.Repository
public class Repository {

    JdbcTemplate jdbcTemplate;

    public Repository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> search(String keyWord) {

        //final SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("SELECT id, name FROM product WHERE name LIKE 'j%'");
        final List<Map<String, Object>> maps = jdbcTemplate.queryForList("SELECT id, name FROM product WHERE name LIKE '" + keyWord + "%'");

        return maps.stream()
                .map(row -> product(row))
                .collect(Collectors.toList());

      /*  return jdbcTemplate.queryForList("SELECT id, name FROM product WHERE name LIKE '{0}'",
                new String[]{keyWord}, Product.class);*/
    }




    public Product product(Map<String, Object> row) {
        return Product.builder()
                .id((Integer) row.get("id"))
                .name((String) row.get("name"))
                .build();
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

    /*    entity.setId(sqlRowSet.getInt("id"));
        entity.setName(sqlRowSet.getString("name"));*/

        return Optional.of(entity);
    }

    public List<Product> getAll() {

        final List<Map<String, Object>> query = jdbcTemplate.queryForList("SELECT * FROM Product");

        return query.stream()
                .map(row -> product(row))
                .collect(Collectors.toList());

    }

    class ProductListMapper implements RowMapper<Product> {

        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

            return Product.builder()
                    .id(rs.getInt("id"))
                    .name(rs.getString("name"))
                    .build();
        }
    }
}
