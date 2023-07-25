package com.programming.productservice.controller;

import com.programming.productservice.dto.PendingOrdersRequest;
import com.programming.productservice.service.PendingOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products/pendingorder/create")
public class PendingOrdersController {
    @Autowired
    private PendingOrderService pendingOrderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPendingOrder(@RequestBody PendingOrdersRequest pendingOrderRequest){
        pendingOrderService.createPendingOrders(pendingOrderRequest);
    }


}
