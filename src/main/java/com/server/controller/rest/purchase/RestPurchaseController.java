package com.server.controller.rest.purchase;

import com.server.entity.purchase.Purchase;
import com.server.service.purchase.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class RestPurchaseController implements IPurchaseController {


    private final PurchaseService purchaseService;


    @Autowired
    public RestPurchaseController(PurchaseService purchaseService){
        this.purchaseService = purchaseService;
    }


    @Override
    public ResponseEntity<?> create(int[] goodsId, int[] servicesId){

        Purchase purchase = purchaseService.create(goodsId, servicesId);

        return new ResponseEntity<>(purchase, HttpStatus.CREATED);

    }


    @Override
    public ResponseEntity<?> manualCreate(int[] goodsId, int[] servicesId, String phone){

        Purchase purchase = purchaseService.manualCreate(goodsId, servicesId, phone);

        return new ResponseEntity<>(purchase, HttpStatus.CREATED);

    }


    @Override
    public ResponseEntity<List<Purchase>> readAll() {

        final List<Purchase> purchases = purchaseService.readAll();

        return purchases != null &&  !purchases.isEmpty()
                ? new ResponseEntity<>(purchases, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


    @Override
    public ResponseEntity<Purchase> readId(int id) {

        final Purchase purchase = purchaseService.readId(id);

        return purchase != null
                ? new ResponseEntity<>(purchase, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }


    @Override
    public ResponseEntity<?> update(int id, int[] goodsId, int[] servicesId){

        final Purchase purchase = purchaseService.update(id, goodsId, servicesId);

        return purchase != null
                ? new ResponseEntity<>(purchase, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

    }


    @Override
    public ResponseEntity<?> delete(int id) {

        final boolean deleted = purchaseService.delete(id);

        return deleted
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);

    }

}
