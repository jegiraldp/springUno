package com.jegiraldp.market.web.controller;

import com.jegiraldp.market.domain.Product;
import com.jegiraldp.market.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/all")
    public List<Product> getAll(){
        return productService.getAllProducts();
    }

    @GetMapping("/{productId}")
     public Optional<Product> getProduct(@PathVariable("productId") int productId){
            return productService.getProduct(productId);
     }

     @GetMapping("/category/{categoryId}")
     public Optional<List<Product>> getByCategory(@PathVariable("categoryId") int categoryId){
        return productService.getByCategory(categoryId);
    }

    @PostMapping("/save")
    public Product save(Product product){
        return productService.save(product);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") int productId){
        return productService.delete(productId);
    }


}
