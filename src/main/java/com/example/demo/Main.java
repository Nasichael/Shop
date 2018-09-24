package com.example.demo;

import com.example.demo.domain.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    List<Product> listOfProducts = new ArrayList<>();

    public void addProducts(Product p) {
        listOfProducts.add(p);
    }

    public static Predicate<Product> namePredicate(String name) {
        return product -> product.getName().contains(name);
    }

    public List<Product> searchProduct(String name) {
        List<Product> filteredProducts = listOfProducts
                .stream()
                .filter(namePredicate(name))
                .collect(Collectors.toList());

        System.out.println(filteredProducts.size());
        return filteredProducts;
    }

    public void search(String keyWord) {
        searchProduct(keyWord);
    }

    public static void main(String[] args) {

        /*Product p = Product.builder()
                .category(null)
                .id(3)
                .url("")
                .name("dress")
                .build();

        Product p1 = Product.builder()
                .category(null)
                .id(6)
                .name("Reddress")
                .url("")
                .build();*/

      Main main = new Main();
      main.search("dress");

    }
}
