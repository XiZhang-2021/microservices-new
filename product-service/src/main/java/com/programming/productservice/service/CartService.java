package com.programming.productservice.service;

import com.programming.productservice.dto.CartCreateRequest;
import com.programming.productservice.dto.User;
import com.programming.productservice.model.Cart;
import com.programming.productservice.model.CartProduct;
import com.programming.productservice.repository.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    @Transactional
    public void createCart(CartCreateRequest cartCreateRequest) {
        Cart c = Cart.builder()
                .userid(cartCreateRequest.getUserid())
                .cartProductList(new ArrayList<CartProduct>()).build();
        cartRepository.save(c);
    }

    @Transactional
    public void deleteByDeleteUser(int userid) {
        cartRepository.deleteByUserid(userid);
    }
}
