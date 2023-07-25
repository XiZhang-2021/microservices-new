package com.programming.productservice.service;

import com.programming.productservice.dto.ReviewRequest;
import com.programming.productservice.model.ProductReview;
import com.programming.productservice.repository.ProductReviewRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductReviewService {
    @Autowired
    private ProductReviewRepository productReviewRepository;

    @Transactional
    public void addReview(ReviewRequest reviewRequest){
        System.out.println(reviewRequest);
        ProductReview review = ProductReview.builder()
                .userid(reviewRequest.getUserid())
                .username(reviewRequest.getUsername())
                .productid(reviewRequest.getProductid())
                .productname(reviewRequest.getProductname())
                .productimage(reviewRequest.getProductimage())
                .reviewtime(LocalDateTime.now())
                .productreview(reviewRequest.getProductreview())
                .build();
        System.out.println(review);
        productReviewRepository.save(review);
    }

    @Transactional
    public List<ProductReview> getReview(int productid){
        return productReviewRepository.findAllByProductid(productid);
    }
    @Transactional
    public void deleteByProductid(int productid) {
        productReviewRepository.deleteByProductid(productid);
    }
    @Transactional
    public void deleteByUserid(int userid) {
        productReviewRepository.deleteByUserid(userid);
    }
}
