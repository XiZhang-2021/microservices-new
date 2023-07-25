package com.programming.productservice.repository;

import com.programming.productservice.model.Product;
import com.programming.productservice.model.ProductReview;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Integer> {
    List<ProductReview> findAllByProductid(int productid);

    void deleteByProductid(int productid);

    @Transactional
    @Modifying
    @Query("delete from ProductReview pr where pr.userid =:userid")
    void deleteByUserid(@Param("userid") int userid);
}
