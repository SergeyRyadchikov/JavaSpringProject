package com.server.controller.restControllers.products;

import com.server.dto.products.ServicesDTO;
import com.server.model.products.Product;
import com.server.model.products.ProductCreator;
import com.server.model.products.Services;
import com.server.service.products.ServiceForServicesImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("v1/products")
@Tag(name = "Продукты/Услуги", description = "Все методы для работы с продуктами и услугами")
public class RestServicesController {

    private final ServiceForServicesImpl serviceForServices;
    private final ProductCreator<Services> productCreator;

    @Autowired
    public RestServicesController(ServiceForServicesImpl serviceForServices, ProductCreator<Services> productCreator) {
        this.serviceForServices = serviceForServices;
        this.productCreator = productCreator;
    }

    @RequestMapping(value = "/services", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody ServicesDTO servicesDTO) {
        Services services = productCreator.createProduct();
        services.setProductName(servicesDTO.productName());
        services.setAmount(servicesDTO.amount());
        serviceForServices.create(services);
        return new ResponseEntity<>(services, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/services", method = RequestMethod.GET)
    public ResponseEntity<List<Services>> read() {
        final List<Services> products = serviceForServices.readAll();

        return products != null && !products.isEmpty() ? new ResponseEntity<>(products, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/services/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> read(@PathVariable(name = "id") int id) {
        final Product product = serviceForServices.read(id);

        return product != null ? new ResponseEntity<>(product, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/services/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody ServicesDTO servicesDTO) {
        Services services = productCreator.createProduct();
        services.setProductName(servicesDTO.productName());
        services.setAmount(servicesDTO.amount());
        final boolean updated = serviceForServices.update(services, id);

        return updated ? new ResponseEntity<>(services, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @RequestMapping(value = "/services/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = serviceForServices.delete(id);

        return deleted ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
