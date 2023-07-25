package com.programming.productservice.controller;

import com.programming.productservice.dto.ProductRequest;
import com.programming.productservice.dto.ProductResponse;
import com.programming.productservice.dto.ReviewRequest;
import com.programming.productservice.dto.User;
import com.programming.productservice.model.Product;
import com.programming.productservice.model.ProductReview;
import com.programming.productservice.service.FavoriateService;
import com.programming.productservice.service.ProductReviewService;
import com.programming.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductReviewService productReviewService;

    @Autowired
    private FavoriateService favoriateService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/detail/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getProduct(@PathVariable Integer id){
        return productService.getProduct(id);
    }

//    @DeleteMapping("/delete/{id}")
//    public void deletProduct(@PathVariable Integer id ){
//        productService.deleteProductById(id);
//    }

    @GetMapping("/genre/{genre}")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getByGenre(@PathVariable String genre){
        return productService.getProductsByGenre(genre);
    }

    @GetMapping("/search/{keyword}")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getByKeyword(@PathVariable String keyword){
        return productService.search(keyword);
    }

    @PostMapping("/create/review")
    @ResponseStatus(HttpStatus.CREATED)
    public void createReview(@RequestBody ReviewRequest reviewRequest){
        productReviewService.addReview(reviewRequest);
    }

    @GetMapping("/review/{productid}")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductReview> getProductReview(@PathVariable int productid){
        return productReviewService.getReview(productid);
    }

    @DeleteMapping("/deletereview/user/{userid}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteReview(@PathVariable String userid){
        int uid = Integer.parseInt(userid);
        productReviewService.deleteByUserid(uid);
    }

}
