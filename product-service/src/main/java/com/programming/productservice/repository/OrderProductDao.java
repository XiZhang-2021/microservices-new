package com.programming.productservice.repository;

import com.programming.productservice.model.OrderProduct;
import com.programming.productservice.model.Orders;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderProductDao extends JpaRepository<OrderProduct, Integer> {
    List<OrderProduct> findAllByOrders_id(int ordersid);

}
