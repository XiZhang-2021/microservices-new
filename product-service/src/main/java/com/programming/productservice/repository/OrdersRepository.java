package com.programming.productservice.repository;

import com.programming.productservice.model.Orders;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {
    @Query("select o from Orders o where (o.status = 'Completed' or o.status = 'Cancelled')")
    List<Orders> findCompletedCancelledOrders();

    List<Orders> findAllByUserid(int userid);

    List<Orders> findAllByStatus(String status);

    @Query("select o from Orders o where o.status = 'Pending' and o.id = :id")
    Orders adminSearchPendingOrder(@Param("id") Integer id);

    @Query("select o from Orders o where o.status = 'Confirmed' and o.id = :id")
    Orders adminSearchConfirmedOrder(@Param("id") Integer id);

    @Query("select o from Orders o where (o.status = 'Completed' or o.status = 'Cancelled') and o.id = :id")
    Orders adminSearchCompletedCancelledOrder(@Param("id") Integer id);

    @Transactional
//    @Modifying
//    @Query("delete from Orders o where o.userid = :userid")
    void deleteByUserid( int userid);
}
