package com.server.model.products;


import org.springframework.stereotype.Component;

@Component
public class ServicesCreator implements ProductCreator <Services>{

    @Override
    public Services createProduct() {

        return new Services();
    }
}
