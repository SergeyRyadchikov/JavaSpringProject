package com.server.controller.rest.product;

import com.server.dto.product.ServicesDto;
import com.server.entity.product.Services;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("v1/products")
@Tag(name = "Продукты/Услуги", description = "Все методы для работы с продуктами и услугами")
public interface IServicesController {


    /**
     *
     * @param servicesDto
     * @return
     */
    @RequestMapping(value = "/services", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> create(@RequestBody ServicesDto servicesDto);



    /**
     *
     * @return
     */
    @RequestMapping(value = "/services", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT', 'LEAD')")
    public ResponseEntity<List<Services>> readAll();



    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/services/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT', 'LEAD')")
    public ResponseEntity<Services> readId(@PathVariable(name = "id") int id);



    /**
     *
     * @param id
     * @param servicesDto
     * @return
     */
    @RequestMapping(value = "/services/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody ServicesDto servicesDto);



    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/services/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id);

}
