package com.programming.productservice.controller;

import com.programming.productservice.dto.ProductRequest;
import com.programming.productservice.dto.ProductResponse;
import com.programming.productservice.model.OrderProduct;
import com.programming.productservice.model.Orders;
import com.programming.productservice.model.Product;
import com.programming.productservice.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/products/admin")
public class AdminController {
    @Autowired
    private ProductService productService;
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private CartProductService cartProductService;
    @Autowired
    private FavoriateService favoriateService;
    @Autowired
    private ProductReviewService productReviewService;
    @Autowired
    private S3Service s3Service;


    @PostMapping("/create/book")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest){
        productService.createProduct(productRequest);
    }

    @GetMapping("/orders/{status}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Orders> getOrders(@PathVariable("status") String status){
        if(status == null){
            return null;
        }
        return ordersService.getAllOrdersByStatus(status);
    }

    @GetMapping("/orders/completed/cancelled")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<Orders> getOrders(){
        return ordersService.getAllCompetedCancelledOrders();
    }

    @PostMapping("/orders/changestatus")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void changeStatus(@RequestBody Orders orders){ //pending -> confirmed
        ordersService.changeStatus(orders);
    }

    @PostMapping("/orders/cancel")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void cancel(@RequestBody Orders orders){
        ordersService.cancel(orders);
    }

    @PostMapping("/orders/complete")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void complete(@RequestBody Orders orders){
        ordersService.complete(orders);
    }

    @Autowired
    private OrderProductService orderProductService;
    @GetMapping("/orders/ordersdetail/{ordersid}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<OrderProduct> getOrderProduct(@PathVariable("ordersid") int ordersid){
        return orderProductService.getOrderProduct(ordersid);
    }

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<ProductResponse> getProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/product/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ProductResponse getProducts(@PathVariable("id") int id){
        return productService.getProduct(id);
    }

    @PostMapping("/product/updatestock")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateStock(@RequestBody Product updateProduct){
        productService.updateStock(updateProduct);
    }

    @PostMapping("/product/delete")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deleteProduct(@RequestBody Product deleteProduct){
        //1st delete product
        productService.deleteProductById(deleteProduct.getId());
        //2nd delete cartProduct which contains this product
        cartProductService.deleteByProductid(deleteProduct.getId());
        //3rd delete favoriate which contains this prodcut
        favoriateService.deleteByProductId(deleteProduct.getId());
        //4th delete the review of this product
        productReviewService.deleteByProductid(deleteProduct.getId());
        //not test!

    }

    @PostMapping("/product/changeprice")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void changePrice(@RequestBody Product changePriceProduct){
        productService.updatePrice(changePriceProduct);
    }

    @GetMapping("/products/search/{keyword}")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> adminSearch(@PathVariable String keyword){
        return productService.adminSearch(keyword);
    }

    @GetMapping("/order/pending/search/{keyword}")
    @ResponseStatus(HttpStatus.OK)
    public List<Orders> adminSearchPendingOrder(@PathVariable String keyword){
        return productService.adminSearchPendingOrder(keyword);
    }

    @GetMapping("/order/confirmed/search/{keyword}")
    @ResponseStatus(HttpStatus.OK)
    public List<Orders> adminSearchConfirmedOrder(@PathVariable String keyword){
        return productService.adminSearchConfirmedOrder(keyword);
    }

    @GetMapping("/order/completed/cancelled/search/{keyword}")
    @ResponseStatus(HttpStatus.OK)
    public List<Orders> adminSearchCompletedOrder(@PathVariable String keyword){
        return productService.adminSearchCompletedCancelledOrder(keyword);
    }

    @PostMapping("/order/completed/approve/refund")
    @ResponseStatus(HttpStatus.OK)
    public void approveRefund(@RequestBody OrderProduct purchase){
        orderProductService.approveRefunded(purchase);
    }

    @PostMapping("/order/completed/decline/refund")
    @ResponseStatus(HttpStatus.OK)
    public void declineRefund(@RequestBody OrderProduct purchase){
        orderProductService.declineRefund(purchase);
    }


    @PostMapping("/saveimage")
    @ResponseStatus(HttpStatus.CREATED)
    public String saveImage(@RequestBody MultipartFile file){
        return s3Service.uploadFile(file);
    }
}
