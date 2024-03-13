package com.server.controller.restControllers.product;

import com.server.dto.products.GoodsDto;
import com.server.model.product.Goods;
import com.server.service.product.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class RestGoodsController implements IGoodsController{

    private final GoodsService goodsService;

    @Autowired
    public RestGoodsController(
            GoodsService goodsService
    ) {
        this.goodsService = goodsService;
    }


    @Override
    public ResponseEntity<?> create(GoodsDto goodsDto) {

        Goods goods = goodsService.create(goodsDto);

        return new ResponseEntity<>(goods, HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<List<Goods>> readAll() {

        final List<Goods> goodsList = goodsService.readAll();

        return goodsList != null && !goodsList.isEmpty()
                ? new ResponseEntity<>(goodsList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @Override
    public ResponseEntity<Goods> readId(int id) {

        final Goods goods = goodsService.read(id);

        return goods != null
                ? new ResponseEntity<>(goods, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @Override
    public ResponseEntity<?> update(int id, GoodsDto goodsDto) {

        final Goods goods = goodsService.update(goodsDto, id);

        return goods != null
                ? new ResponseEntity<>(goods, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

    }

    @Override
    public ResponseEntity<?> delete(int id) {

        final boolean deleted = goodsService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

    }

}
