package com.programming.productservice.repository;

import com.programming.productservice.model.PendingOrders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PendingOrdersRepository extends JpaRepository<PendingOrders, Integer> {
}
