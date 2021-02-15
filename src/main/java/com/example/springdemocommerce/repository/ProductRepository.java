package com.example.springdemocommerce.repository;

import com.example.springdemocommerce.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Product,Integer> {
/*    //Filter or Search
    @Query("SELECT p FROM Product p WHERE concat(p.name,p.id,p.price) LIKE %:keyword%")
    List<Product> findAll(@Param("keyword") String keyword);*/

    //Paging and sorting
    @Query("SELECT p FROM Product p WHERE concat(p.name,p.id,p.price) LIKE %:keyword%")
    Page<Product> findAll(String keyword, Pageable pageable);

}
