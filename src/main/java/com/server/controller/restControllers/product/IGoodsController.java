package com.server.controller.restControllers.product;

import com.server.dto.products.GoodsDto;
import com.server.model.product.Goods;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/products")
@Tag(
        name = "Продукты/Услуги",
        description = "Все методы для работы с продуктами и устлугами"
)
public interface IGoodsController {

    @RequestMapping(value = "/goods", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody GoodsDto goodsDto);

    @RequestMapping(value = "/goods", method = RequestMethod.GET)
    public ResponseEntity<List<Goods>> readAll();

    @RequestMapping(value = "/goods/{id}", method = RequestMethod.GET)
    public ResponseEntity<Goods> readId(@PathVariable(name = "id") int id);

    @RequestMapping(value = "/goods/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody GoodsDto goodsDto);

    @RequestMapping(value = "/goods/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id);
}
