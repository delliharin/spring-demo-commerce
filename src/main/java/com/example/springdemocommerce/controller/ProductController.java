package com.example.springdemocommerce.controller;

import com.example.springdemocommerce.entity.Product;
import com.example.springdemocommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> list() {
        return productService.listAll().getContent();
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> get(@PathVariable Integer id) {
        try {
            Product product = productService.get(id);
            return new ResponseEntity<Product>(product, HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/products")
    public void add(@RequestBody Product product) {
        productService.save(product);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<?> update(@RequestBody Product product, @PathVariable Integer id) {
        try {
            Product existProduct = productService.get(id);
            productService.save(product);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable Integer id) {
        productService.delete(id);
    }

    @GetMapping("/products/search/{keyword}")
    public List<Product> searchList(@PathVariable String keyword) {
        return productService.listAll(keyword).getContent();
    }

    @GetMapping("/products/paging")
    public Page<Product> paging(@RequestParam(required = false) String keyword, @RequestParam String sortField,
                                @RequestParam Integer pageNumber,
                                @RequestParam String sortDir) {
        return productService.pagingAndSortingList(keyword, sortField, pageNumber, sortDir);
    }
}
