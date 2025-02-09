package com.jegiraldp.market.web.controller;

import com.jegiraldp.market.domain.Product;
import com.jegiraldp.market.domain.Purchase;
import com.jegiraldp.market.domain.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    PurchaseService purchaseService;

    @GetMapping("/all")
    public ResponseEntity<List<Purchase>> getAll(){
        return new ResponseEntity<>(purchaseService.getAllPurchases(), HttpStatus.OK);
    }

    @GetMapping("/client/{idClient}")
    public ResponseEntity<List<Purchase>> getByClient(@PathVariable("idClient") String clientId){
        return ResponseEntity.of(purchaseService.getByClient(clientId));
    }

    @PostMapping("/save")
    public ResponseEntity<Purchase> save(@RequestBody Purchase purchase){
        return new ResponseEntity<>(purchaseService.save(purchase), HttpStatus.CREATED);
    }

}
