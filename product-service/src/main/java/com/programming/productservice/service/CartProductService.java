package com.programming.productservice.service;

import com.programming.productservice.dto.CartAddProductRequest;
import com.programming.productservice.model.Cart;
import com.programming.productservice.model.CartProduct;
import com.programming.productservice.model.Product;
import com.programming.productservice.repository.CartProductDao;
import com.programming.productservice.repository.CartRepository;
import com.programming.productservice.repository.ProductRepository;
//import jakarta.transaction.Transactional;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartProductService {
    @Autowired
    private CartProductDao cartProductDao;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;
    public void addCartProduct(CartAddProductRequest cartAddProductRequest){
        long pid = cartAddProductRequest.getProductid();
        Optional<Product> op = productRepository.findById(pid);
        Product p = op.get();
        List<Product> list = new ArrayList<>();
        list.add(p);
        Cart cart = cartRepository.findByUserid(cartAddProductRequest.getUserid());
        CartProduct cartProduct = CartProduct.builder()
                .cart(cart)
                .product(p)
                .description(cartAddProductRequest.getDescription())
                .name(cartAddProductRequest.getName())
                .image(cartAddProductRequest.getImage())
                .quantity(cartAddProductRequest.getQuantity())
                .price(cartAddProductRequest.getPrice())
                .total(cartAddProductRequest.getTotal())
                .build();
        cartProductDao.save(cartProduct);
    }
    @Transactional
    public List<CartProduct> findByUserid(int userid) {
        Cart cart = cartRepository.findByUserid(userid);
        if(cart==null){
            return null;
        }
        int cartid = cart.getId();
        return cartProductDao.findByCart_Id(cartid);
    }

    @Transactional
    public void deleteById(int id){
        try{
            cartProductDao.deleteById(id);
            cartProductDao.flush();
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
    }

    @Transactional
    public void deleteByProductid(int productid){
        cartProductDao.deleteByProduct_Id(productid);
    }
}
