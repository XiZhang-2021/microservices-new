package com.programming.productservice.service;

import com.programming.productservice.model.OrderProduct;
import com.programming.productservice.model.Orders;
import com.programming.productservice.model.Product;
import com.programming.productservice.repository.OrderProductDao;
import com.programming.productservice.repository.OrdersRepository;
import com.programming.productservice.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderProductService {
    @Autowired
    private OrderProductDao orderProductDao;
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private ProductRepository productRepository;


    @Transactional
    public List<OrderProduct> getOrderProduct(int ordersid){
        return orderProductDao.findAllByOrders_id(ordersid);
    }
    @Transactional
    public void cancel(OrderProduct orderProduct) {
        int id = orderProduct.getId();
        Optional<OrderProduct> orderproductTarget = orderProductDao.findById(id);
        if(orderproductTarget.isPresent()){
            OrderProduct targetOrderProduct = orderproductTarget.get();
            targetOrderProduct.setStatus("Cancelled");
            int ordersid = targetOrderProduct.getOrders().getId();
            Optional<Orders> ordersOp = ordersRepository.findById(ordersid);
            if(ordersOp.isPresent()){
                ordersOp.get().setTotalprice(ordersOp.get().getTotalprice().subtract(targetOrderProduct.getTotal()));
                ordersRepository.save(ordersOp.get());
            }
            orderProductDao.save(targetOrderProduct);
        }
    }


    @Transactional
    public void refunding(OrderProduct orderProduct) {
        int id = orderProduct.getId();
        Optional<OrderProduct> orderproductTarget = orderProductDao.findById(id);
        if(orderproductTarget.isPresent() && orderproductTarget.get().getStatus().equals("Completed")){
            orderproductTarget.get().setStatus("Refunding");
        }
        orderProductDao.save(orderproductTarget.get());
    }
    @Transactional
    public void approveRefunded(OrderProduct purchase) {
        int id = purchase.getId();
        Optional<OrderProduct> targetOp = orderProductDao.findById(id);
        if(targetOp.isPresent()){
            OrderProduct target = targetOp.get();
            target.setStatus("Refunded");
            Integer pid = target.getProduct().getId();
            Optional<Product> productOp = productRepository.findById(pid.longValue());
            if(productOp.isPresent()){
                Product product = productOp.get();
                product.setStock(product.getStock() + target.getQuantity());
                productRepository.save(product);
            }
            int oid = target.getOrders().getId();
            Optional<Orders> ordersOp = ordersRepository.findById(oid);
            if(ordersOp.isPresent()){
                Orders orders = ordersOp.get();
                orders.setTotalprice(orders.getTotalprice().subtract(target.getTotal()));
                ordersRepository.save(orders);
            }
            orderProductDao.save(target);
        }
    }
    @Transactional
    public void declineRefund(OrderProduct purchase) {
        int id = purchase.getId();
        Optional<OrderProduct> targetOp = orderProductDao.findById(id);
        if(targetOp.isPresent()){
            OrderProduct target = targetOp.get();
            target.setStatus("Completed");
            orderProductDao.save(target);
        }
    }
    @Transactional
    public List<OrderProduct> getAll() {
        return orderProductDao.findAll();
    }


}
