package com.programming.productservice.service;

import com.programming.productservice.dto.PendingOrdersRequest;
import com.programming.productservice.model.OrderProduct;
import com.programming.productservice.model.PendingOrderProduct;
import com.programming.productservice.model.PendingOrders;
import com.programming.productservice.repository.PendingOrderProductDao;
import com.programming.productservice.repository.PendingOrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PendingOrderService {
    @Autowired
    private PendingOrdersRepository pendingOrdersRepository;

    @Autowired
    private PendingOrderProductDao pendingOrderProductDao;

    public void createPendingOrders(PendingOrdersRequest pendingOrdersRequest){
        PendingOrders pendingOrders = PendingOrders.builder()
                .userid(pendingOrdersRequest.getUserid())
                .username(pendingOrdersRequest.getUsername())
                .orderaddress(pendingOrdersRequest.getOrderaddress())
                .orderpaymentinfo(pendingOrdersRequest.getOrderpaymentinfo())
                .ordertime(LocalDateTime.now())
                .totalprice(pendingOrdersRequest.getTotalprice())
                .status("Pending")
                .build();
        PendingOrders savePendingOrder = pendingOrdersRepository.save(pendingOrders);

        List<PendingOrderProduct> purchases = pendingOrdersRequest.getPurchases();
        for(PendingOrderProduct pendingOrderProduct : purchases){
            pendingOrderProduct.setPendingOrders(savePendingOrder);
            pendingOrderProduct.setStatus("Pending");
        }
        savePendingOrder.setPurchases(purchases);
        pendingOrdersRepository.save(savePendingOrder);

    }
}
