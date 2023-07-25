package com.programming.productservice.repository;

import com.programming.productservice.model.Cart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Query("select c from Cart c where c.userid = :userid")
    public Cart findByUserid(@Param("userid") int userid);

    @Transactional
    @Modifying
    @Query("delete from Cart c where c.userid =:userid")
    void deleteByUserid(@Param("userid") int userid);
}
