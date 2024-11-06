package com.rs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean
    @Scope("prototype")
    public Product product1() {
        Product product = new Product(1, "Product1", 100.0, "Description of Product 1");
        return product;
    }

    @Bean
    @Scope("prototype")
    public Product product2() {
        return new Product(2, "Product2", 150.0, "Description of Product 2");
    }

    @Bean
    @Scope("singleton")
    public Product product3() {
        Product product = new Product(3, "Product3", 200.0, "Description of Product 3");
        return product;
    }
}
