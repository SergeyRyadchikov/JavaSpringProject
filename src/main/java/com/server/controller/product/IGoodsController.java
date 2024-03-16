package com.server.controller.product;

import com.server.dto.product.GoodsDto;
import com.server.entity.product.Goods;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("v1/products")
@Tag(
        name = "Продукты/Услуги",
        description = "Все методы для работы с продуктами и устлугами"
)
public interface IGoodsController {

    /**
     *
     * @param goodsDto
     * @return
     */
    @RequestMapping(value = "/goods", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> create(@RequestBody GoodsDto goodsDto);



    /**
     *
     * @return
     */
    @RequestMapping(value = "/goods", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT', 'LEAD')")
    public ResponseEntity<List<Goods>> readAll();



    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/goods/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT', 'LEAD')")
    public ResponseEntity<Goods> readId(@PathVariable(name = "id") int id);



    /**
     *
     * @param id
     * @param goodsDto
     * @return
     */
    @RequestMapping(value = "/goods/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody GoodsDto goodsDto);



    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/goods/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id);
}
