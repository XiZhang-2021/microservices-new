package com.programming.productservice.service;

import com.programming.productservice.dto.ProductRequest;
import com.programming.productservice.dto.ProductResponse;
import com.programming.productservice.model.Orders;
import com.programming.productservice.model.Product;
import com.programming.productservice.repository.OrdersRepository;
import com.programming.productservice.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrdersRepository ordersRepository;
    @Transactional
    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.getName())
                .author(productRequest.getAuthor())
                .timeversion(productRequest.getTimeversion())
                .genre(productRequest.getGenre())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .stock(productRequest.getStock())
                .image(productRequest.getImage())
                .build();

        productRepository.save(product);
        log.info("Product " + product.getId() + " is saved");
    }
    @Transactional
    public List<ProductResponse> getAllProducts() {

        List<Product> products = productRepository.findAll();

        return products.stream().map(product -> {
            return mapToProductResponse(product);
        }).collect(Collectors.toList());

    }

    public ProductResponse mapToProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .author(product.getAuthor())
                .genre(product.getGenre())
                .timeversion(product.getTimeversion())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .image(product.getImage())
                .build();
    }
    @Transactional
    public void deleteProductById(Integer id) {
        productRepository.deleteById(id.longValue());

    }

    @Transactional
    public ProductResponse getProduct(Integer id) {
        Optional<Product> p = productRepository.findById(id.longValue());
        if(p.isPresent()){
            return mapToProductResponse(p.get());
        }else{
            throw new RuntimeException("Product with id " + id + " not found");
        }
    }
    @Transactional
    public void updateStock(Product updateProduct) {
        Integer id = updateProduct.getId();
        Optional<Product> op = productRepository.findById(id.longValue());
        if(op.isPresent()){
            op.get().setStock(updateProduct.getStock());
            productRepository.save(op.get());
        }
    }

    public void updatePrice(Product changePriceProduct) {
        Integer id = changePriceProduct.getId();
        Optional<Product> op = productRepository.findById(id.longValue());
        if(op.isPresent()){
            op.get().setPrice(changePriceProduct.getPrice());
            productRepository.save(op.get());
        }
    }
    @Transactional
    public List<Product> getProductsByGenre(String genre) {
        return productRepository.findByGenre(genre);
    }

    @Transactional
    public List<Product> search(String keyword) {
        return productRepository.search(keyword);
    }

    @Transactional
    public List<Product> adminSearch(String keyword) {
        List<Product> products = productRepository.adminSearch(keyword);
        try{
            Integer i = Integer.parseInt(keyword);
            Optional<Product> product = productRepository.findById(i.longValue());
            if(product.isPresent()){
                products.add(product.get());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return products;
    }

    @Transactional
    public List<Orders> adminSearchPendingOrder(String keyword) {
        List<Orders> list = new ArrayList<>();
        try{
            Integer id = Integer.parseInt(keyword);
            Orders orders = ordersRepository.adminSearchPendingOrder(id);
            list.add(orders);

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    @Transactional
    public List<Orders> adminSearchConfirmedOrder(String keyword) {
        List<Orders> list = new ArrayList<>();
        try{
            Integer id = Integer.parseInt(keyword);
            Orders orders = ordersRepository.adminSearchConfirmedOrder(id);
            list.add(orders);

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public List<Orders> adminSearchCompletedCancelledOrder(String keyword) {
        List<Orders> list = new ArrayList<>();
        try{
            Integer id = Integer.parseInt(keyword);
            Orders orders = ordersRepository.adminSearchCompletedCancelledOrder(id);
            list.add(orders);

        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
