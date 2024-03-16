package com.server.controller.restControllers;

import com.server.model.Purchase;
import com.server.model.product.Goods;
import com.server.service.user.clientsService.ClientService;
import com.server.service.PurchaseServiceImpl;
import com.server.service.product.GoodsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/purchase")
@Tag(
        name = "Заказы",
        description = "Все методы для работы с заказами"
)
public class RestPurchaseController {


    private final PurchaseServiceImpl purchaseService;
    private final GoodsService productService;
    private final ClientService clientService;

    @Autowired
    public RestPurchaseController(
            PurchaseServiceImpl purchaseService,
            GoodsService productService,
            ClientService clientService
    ) {
        this.purchaseService = purchaseService;
        this.productService = productService;
        this.clientService = clientService;
    }

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT')")
    public ResponseEntity<?> create(@RequestParam("client id")int clientId, @RequestParam("product id")int[] productId)
    {
        List<Goods> products = new ArrayList<>();
        for (int i = 0; i < productId.length; i++ ){
            products.add(productService.read(productId[i]));
        }
        Purchase purchase = new Purchase(products, clientService.read(clientId));
        purchaseService.create(purchase);
        return new ResponseEntity<>(purchase, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<List<Purchase>> read() {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.getAuthentication().getName();
        System.out.println("Пользователь: " + securityContext.getAuthentication().getName());

        final List<Purchase> purchases = purchaseService.readAll();

        return purchases != null &&  !purchases.isEmpty()
                ? new ResponseEntity<>(purchases, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT')")
    public ResponseEntity<Purchase> read(@PathVariable(name = "id") int id) {
        final Purchase purchase = purchaseService.read(id);

        return purchase != null
                ? new ResponseEntity<>(purchase, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT')")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Purchase purchase) {
        final boolean updated = purchaseService.update(purchase, id);

        return updated
                ? new ResponseEntity<>(purchase, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAnyAuthority('ADMIN', 'CLIENT')")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = purchaseService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
