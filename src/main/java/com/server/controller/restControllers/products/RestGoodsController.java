package com.server.controller.restControllers.products;

import com.server.dto.products.GoodsDTO;
import com.server.model.products.*;
import com.server.service.products.GoodsServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/products")
@Tag(
        name = "Продукты/Услуги",
        description = "Все методы для работы с продуктами и устлугами"
)
public class RestGoodsController {

    private final GoodsServiceImpl goodsService;
    private final ProductCreator<Goods> productCreator;

    @Autowired
    public RestGoodsController(
            GoodsServiceImpl goodsService,
            ProductCreator<Goods> productCreator
    ) {
        this.goodsService = goodsService;
        this.productCreator = productCreator;
    }

    @RequestMapping(value = "/goods", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody GoodsDTO goodsDTO) {
        Goods goods = productCreator.createProduct();
        goods.setProductName(goodsDTO.productName());
        goods.setCategory(goodsDTO.category());
        goods.setAmount(goodsDTO.amount());
        goodsService.create(goods);
        return new ResponseEntity<>(goods, HttpStatus.CREATED);

    }

    @RequestMapping(value = "/goods", method = RequestMethod.GET)
    public ResponseEntity<List<Goods>> read() {
        final List<Goods> products = goodsService.readAll();

        return products != null && !products.isEmpty()
                ? new ResponseEntity<>(products, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/goods/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> read(@PathVariable(name = "id") int id) {
        final Product product = goodsService.read(id);

        return product != null
                ? new ResponseEntity<>(product, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/goods/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody GoodsDTO goodsDTO) {
        Goods goods = productCreator.createProduct();
        goods.setProductName(goodsDTO.productName());
        goods.setCategory(goodsDTO.category());
        goods.setAmount(goodsDTO.amount());
        final boolean updated = goodsService.update(goods, id);

        return updated
                ? new ResponseEntity<>(goods, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @RequestMapping(value = "/goods/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = goodsService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
