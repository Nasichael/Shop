package com.example.demo;

import com.example.demo.domain.Product;
import com.example.demo.shop.products.PostgresProductsRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Optional;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run (DemoApplication.class, args);
    }


    @Bean
    ApplicationRunner run(PostgresProductsRepository repository) {
        return (a) ->
        {
            final Optional<Product> product = repository.find (0);
            System.out.println (product);
        };
    }

}
