package com.server.controller.restControllers;

import com.server.model.Product;
import com.server.model.Purchase;
import com.server.service.ClientServiceImpl;
import com.server.service.ProductServiceImpl;
import com.server.service.PurchaseServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    private final ProductServiceImpl productService;
    private final ClientServiceImpl clientService;

    @Autowired
    public RestPurchaseController(PurchaseServiceImpl purchaseService, ProductServiceImpl productService, ClientServiceImpl clientService) {
        this.purchaseService = purchaseService;
        this.productService = productService;
        this.clientService = clientService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestParam("client id")int clientId, @RequestParam("product id")int[] productId)
    {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < productId.length; i++ ){
            products.add(productService.read(productId[i]));
        }
        Purchase purchase = new Purchase(products, clientService.read(clientId));
        purchaseService.create(purchase);
        return new ResponseEntity<>(purchase, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Purchase>> read() {
        final List<Purchase> purchases = purchaseService.readAll();

        return purchases != null &&  !purchases.isEmpty()
                ? new ResponseEntity<>(purchases, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Purchase> read(@PathVariable(name = "id") int id) {
        final Purchase purchase = purchaseService.read(id);

        return purchase != null
                ? new ResponseEntity<>(purchase, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody Purchase purchase) {
        final boolean updated = purchaseService.update(purchase, id);

        return updated
                ? new ResponseEntity<>(purchase, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id) {
        final boolean deleted = purchaseService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }
}
