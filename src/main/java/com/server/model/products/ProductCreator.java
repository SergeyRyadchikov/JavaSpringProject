package com.server.model.products;

import org.springframework.stereotype.Component;

@Component
public interface ProductCreator <T extends Product> {

    T createProduct();

}
