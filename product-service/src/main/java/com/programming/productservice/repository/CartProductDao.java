package com.programming.productservice.repository;

import com.programming.productservice.model.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartProductDao extends JpaRepository<CartProduct, Integer> {

    List<CartProduct> findByCart_Id(int cartid);


    void deleteByProduct_Id(int productid);
}
