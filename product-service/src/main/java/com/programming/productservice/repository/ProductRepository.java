package com.programming.productservice.repository;

import com.programming.productservice.dto.ProductResponse;
import com.programming.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByGenre(String genre);
    @Query("select p from Product p where lower(p.name) like lower(concat('%', :keyword, '%')) OR lower(p.author) like lower(concat('%', :keyword, '%')) OR lower(p.description) like lower(concat('%', :keyword, '%'))")
    List<Product> search(@Param("keyword") String keyword);

    @Query("select p from Product p where lower(p.name) like lower(concat('%', :keyword, '%')) OR lower(p.author) like lower(concat('%', :keyword, '%')) OR lower(p.description) like lower(concat('%', :keyword, '%'))")
    List<Product> adminSearch(@Param("keyword") String keyword);
}
