package com.server.controller.restControllers.product;

import com.server.dto.products.ServicesDto;
import com.server.model.product.Services;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("v1/products")
@Tag(name = "Продукты/Услуги", description = "Все методы для работы с продуктами и услугами")
public interface IServicesController {

    @RequestMapping(value = "/services", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody ServicesDto servicesDto);


    @RequestMapping(value = "/services", method = RequestMethod.GET)
    public ResponseEntity<List<Services>> readAll();


    @RequestMapping(value = "/services/{id}", method = RequestMethod.GET)
    public ResponseEntity<Services> readId(@PathVariable(name = "id") int id);


    @RequestMapping(value = "/services/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody ServicesDto servicesDto);


    @RequestMapping(value = "/services/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id);

}
