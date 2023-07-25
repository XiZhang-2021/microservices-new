package com.programming.productservice.repository;

import com.programming.productservice.model.PendingOrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PendingOrderProductDao extends JpaRepository<PendingOrderProduct, Integer> {
}
