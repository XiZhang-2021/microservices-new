package com.programming.productservice.service;

import com.programming.productservice.dto.OrderRequest;
import com.programming.productservice.model.OrderProduct;
import com.programming.productservice.model.Orders;
import com.programming.productservice.model.Product;
import com.programming.productservice.repository.OrderProductDao;
import com.programming.productservice.repository.OrdersRepository;
import com.programming.productservice.repository.ProductDao;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private OrderProductDao orderProductDao;
    @Autowired
    private ProductDao productDao;


    @Transactional
    public void createOrder(OrderRequest orderRequest){
        System.out.println(orderRequest.getUserid() + orderRequest.getUsername());
        Orders order = Orders.builder()
                .userid(orderRequest.getUserid())
                .username(orderRequest.getUsername())
                .orderaddress(orderRequest.getOrderaddress())
                .orderpaymentinfo(orderRequest.getOrderpaymentinfo())
                .ordertime(LocalDateTime.now())
                .totalprice(orderRequest.getTotalprice())
                .purchases(orderRequest.getPurchases())
                .status("Pending")
                .build();
        Orders tempOrders = ordersRepository.save(order);

        List<OrderProduct> purchases = tempOrders.getPurchases();
        for(OrderProduct purchase : purchases){
            purchase.setOrders(tempOrders);
            purchase.setStatus("Pending");
            purchase.setOrdertime(tempOrders.getOrdertime());
        }
        tempOrders.setPurchases(purchases);
        ordersRepository.save(tempOrders);
    }

    @Transactional
    public List<Orders> getOrders(int userid){
        return ordersRepository.findAllByUserid(userid);
    }

    @Transactional
    public List<Orders> getAllOrdersByStatus(String status){
        return ordersRepository.findAllByStatus(status);
    }

    @Transactional
    public void changeStatus(Orders orders){
        Optional<Orders> op = ordersRepository.findById(orders.getId());
        if(op.isPresent()){
            Orders o = op.get();
            o.setStatus("Confirmed");
            List<OrderProduct> purchases = o.getPurchases();
            for(OrderProduct purchase : purchases){
                if(purchase.getStatus().equals("Pending")){
                    int qty = purchase.getQuantity();
                    int pid = purchase.getProduct().getId();
                    purchase.setStatus("Confirmed");
                    Optional<Product> optionalProduct = productDao.findById(pid);
                    if(optionalProduct.isPresent()){
                        optionalProduct.get().setStock(optionalProduct.get().getStock()-qty);
                        productDao.save(optionalProduct.get());
                    }
                }
            }
            ordersRepository.save(o);
        }else {
            throw new RuntimeException();
        }
    }
    @Transactional
    public void cancel(Orders orders) {
        Optional<Orders> op = ordersRepository.findById(orders.getId());
        if(op.isPresent()){
            Orders o = op.get();
            o.setStatus("Cancelled");
            List<OrderProduct> purchases = o.getPurchases();
            for(OrderProduct purchase : purchases){
                purchase.setStatus("Cancelled");
            }
            o.setTotalprice(BigDecimal.valueOf(0));
            ordersRepository.save(o);
        }else {
            throw new RuntimeException();
        }
    }

    @Transactional
    public void complete(Orders orders) { //has problem, need to fix
        Optional<Orders> op = ordersRepository.findById(orders.getId());
        if(op.isPresent()){
            Orders o = op.get();
            o.setStatus("Completed");
            List<OrderProduct> purchases = o.getPurchases();
            for(OrderProduct purchase : purchases){
                if(purchase.getStatus().equals("Confirmed")){
                    purchase.setStatus("Completed");
                }
            }
            ordersRepository.save(o);
        } else {
            throw new RuntimeException();
        }
    }
    @Transactional
    public Orders getOrdersByOrdersid(int id) {
        Optional<Orders> op = ordersRepository.findById(id);
        if(op.isPresent()){
            return op.get();
        }else{
            return null;
        }
    }

    @Transactional
    public List<Orders> getAllCompetedCancelledOrders() {
        return ordersRepository.findCompletedCancelledOrders();
    }

    @Transactional
    public void deleteByUserid(int userid) {
        ordersRepository.deleteByUserid(userid);
    }
}
