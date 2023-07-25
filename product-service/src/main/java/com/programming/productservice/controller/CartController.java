package com.programming.productservice.controller;

import com.programming.productservice.dto.CartAddProductRequest;
import com.programming.productservice.dto.CartCreateRequest;
import com.programming.productservice.dto.User;
import com.programming.productservice.model.CartProduct;
import com.programming.productservice.service.CartProductService;
import com.programming.productservice.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private CartProductService cartProductService;
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCart(@RequestBody CartCreateRequest cartCreateRequest){
        System.out.println("Received request to create cart: " + cartCreateRequest);
        cartService.createCart(cartCreateRequest);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addProduct(@RequestBody CartAddProductRequest cartAddProductRequest){
        cartProductService.addCartProduct(cartAddProductRequest);
    }

    @GetMapping("/{userid}")
    @ResponseStatus(HttpStatus.OK)
    public List<CartProduct> getAllCartProduct(@PathVariable int userid){
        return cartProductService.findByUserid(userid);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCartProduct(@PathVariable int id){
        cartProductService.deleteById(id);
    }

    @DeleteMapping("/delete/user/{userid}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCartByDeleteUser(@PathVariable String userid){
        int uid = Integer.parseInt(userid);
        cartService.deleteByDeleteUser(uid);
    }
}
