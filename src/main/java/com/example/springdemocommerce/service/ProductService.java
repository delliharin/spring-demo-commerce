package com.example.springdemocommerce.service;

import com.example.springdemocommerce.entity.Product;
import com.example.springdemocommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Page<Product> listAll() {
        Pageable pageable = PageRequest.of(0,10);
        return productRepository.findAll(pageable);
    }

    public Page<Product> listAll(String keyword) {
        Pageable pageable = PageRequest.of(0,10);
        return productRepository.findAll(keyword,pageable);
    }

    public Page<Product> pagingAndSortingList(String keyword, String sortField, Integer currentPage, String sortDir) {
        Sort sort = Sort.by(sortField);
        sort = sortDir.equals("asc") ? sort.ascending() : sort.descending();
        Pageable pageable = PageRequest.of(currentPage - 1, 4, sort);
        if (keyword != null) {
            return productRepository.findAll(keyword, pageable);
        }
        return productRepository.findAll(pageable);
    }

    public void save(Product product) {
        productRepository.save(product);
    }

    public Product get(Integer id) {
        return productRepository.findById(id).get();
    }

    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

}
