package com.server.model.products;

import org.springframework.stereotype.Component;

@Component
public class GoodsCreator implements ProductCreator <Goods> {


    @Override
    public Goods createProduct() {

        return new Goods();

    }
}
