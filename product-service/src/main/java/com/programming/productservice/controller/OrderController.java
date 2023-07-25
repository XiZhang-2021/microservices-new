package com.programming.productservice.controller;

import com.programming.productservice.dto.OrderRequest;
import com.programming.productservice.dto.OrderResponse;
import com.programming.productservice.dto.User;
import com.programming.productservice.model.OrderProduct;
import com.programming.productservice.model.Orders;
import com.programming.productservice.service.OrderProductService;
import com.programming.productservice.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@RequestMapping("api/products/order")
public class OrderController {
    @Autowired
    private OrdersService orderService;

    @Autowired
    private OrderProductService orderProductService;


    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody OrderRequest orderRequest){
        orderService.createOrder(orderRequest);
    }

    @GetMapping("/{userid}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Orders> getOrders(@PathVariable("userid") int userid){
        return orderService.getOrders(userid);
    }

    @DeleteMapping("/delete/{userid}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUserCascade(@PathVariable String userid){
        int uid = Integer.parseInt(userid);
        orderService.deleteByUserid(uid);
    }

}
