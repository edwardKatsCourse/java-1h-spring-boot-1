package com.telran.secondspringbootproject.controller;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

//      /products
@RestController
public class ProductController {

    /**
     * http://localhost:8080/products/keyboard
     * http://localhost:8080/products/path_variable/details
     *
     */
    @GetMapping(value = "/products/{name}")
    public String getProduct(@PathVariable("name") String name /*keyboard*/) {
        return "My product name is: " + name;
    }

    /**
     * http://localhost:8080/request-parameter?id=15&name=product%20name
     */
    @GetMapping(value = "/request-parameter")
    public String getByRequestParameter(@RequestParam("id") Integer id /*15*/,
                                        @RequestParam("name") String name) {
        return String.format("Product ID: [%s], Product name: [%s]\n",
                id,
                name);
    }

    static List<Product> products = new ArrayList<>();

    @GetMapping("/product-json")
    public Product getJsonProduct(@RequestParam("name") String name,
                                  @RequestParam("seller") String seller) {
        Product product = Product.builder()
                .id(products.size() + 1)
                .name(name)
                .seller(seller)
                .build();

        products.add(product);

        return product;
    }

    @GetMapping("/product-json-get")
    public List<Product> getAllProducts() {
        return products;
    }

}


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
class Product {
    private Integer id;
    private String name;
    private String seller;
}