package com.example.demo.shop.products;

import com.example.demo.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public class PostgresProductsRepository implements ProductsRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Product> find(int id) {
        //  jdbcTemplate.
        return jdbcTemplate.queryForObject ("select * from Product where id=?", new Object[]{
                        id
                },
                new ProductMapper ());
    }

    class ProductMapper implements RowMapper<Optional<Product>> {

        @Override
        public Optional<Product> mapRow(ResultSet rs, int rowNum) throws SQLException {

            Product entity = new Product ();
            entity.setId (rs.getInt ("id"));
            entity.setName (rs.getString ("name"));
            return Optional.of (entity);
        }

    }


}
