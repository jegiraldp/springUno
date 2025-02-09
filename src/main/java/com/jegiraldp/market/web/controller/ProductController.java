package com.jegiraldp.market.web.controller;

import com.jegiraldp.market.domain.Product;
import com.jegiraldp.market.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAll(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{productId}")
     public ResponseEntity<Product> getProduct(@PathVariable("productId") int productId){
            return ResponseEntity.of(productService.getProduct(productId));
     }

     @GetMapping("/category/{categoryId}")
     public ResponseEntity<List<Product>> getByCategory(@PathVariable("categoryId") int categoryId){
        return ResponseEntity.of(productService.getByCategory(categoryId));
                //productService.getByCategory(categoryId);
    }

    @PostMapping("/save")
    /*public ResponseEntity<Product> save(@RequestBody Product product){
        return new ResponseEntity<>(productService.save(product),HttpStatus.CREATED);
    }*/
    public Product saveProduct(@RequestBody Product product){
        return productService.save(product);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id") int productId){
        if(productService.delete(productId)){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }


}
