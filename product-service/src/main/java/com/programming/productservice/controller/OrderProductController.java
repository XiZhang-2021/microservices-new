package com.programming.productservice.controller;

import com.programming.productservice.model.OrderProduct;
import com.programming.productservice.service.OrderProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/products/historyorderproduct")
public class OrderProductController {
    @Autowired
    private OrderProductService orderProductService;
    @GetMapping("/{ordersid}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<OrderProduct> getOrderProduct(@PathVariable("ordersid") int ordersid){
        return orderProductService.getOrderProduct(ordersid);
    }

    @PostMapping("/pending/cancel")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void cancel(@RequestBody OrderProduct orderProduct){
        orderProductService.cancel(orderProduct);
    }

    @PostMapping("/confirmed/refunding")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void refunding(@RequestBody OrderProduct orderProduct){
        orderProductService.refunding(orderProduct);
    }

    @GetMapping("/getall")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<OrderProduct> getAll(){
        return orderProductService.getAll();
    }
}
