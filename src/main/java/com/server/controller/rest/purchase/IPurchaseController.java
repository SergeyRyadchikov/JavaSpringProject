package com.server.controller.rest.purchase;

import com.server.entity.purchase.Purchase;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/purchase")
@Tag(
        name = "Заказы",
        description = "Все методы для работы с заказами"
)
public interface IPurchaseController {

    /**
     * '
     * @param goodsId
     * @param servicesId
     * @return
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT')")
    public ResponseEntity<?> create(
            @RequestParam("goods")int[] goodsId,
            @RequestParam("services")int[] servicesId);



    /**
     *
     * @param goodsId
     * @param servicesId
     * @param phone
     * @return
     */
    @RequestMapping(value = "/manual-create", method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<?> manualCreate(
            @RequestParam("goods")int[] goodsId,
            @RequestParam("services")int[] servicesId,
            @RequestParam("phone")String phone);



    /**
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT')")
    public ResponseEntity<List<Purchase>> readAll();



    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT')")
    public ResponseEntity<Purchase> readId(@PathVariable(name = "id") int id);



    /**
     *
     * @param id
     * @param goodsId
     * @param servicesId
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT')")
    public ResponseEntity<?> update(
            @PathVariable(name = "id") int id,
            @RequestParam("goods")int[] goodsId,
            @RequestParam("services")int[] servicesId);



    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT')")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id);

}
