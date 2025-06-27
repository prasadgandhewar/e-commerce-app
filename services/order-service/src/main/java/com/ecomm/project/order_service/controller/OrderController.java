package com.ecomm.project.order_service.controller;

import com.ecomm.project.order_service.kafka.OrderEventPublisher;
import com.ecomm.project.order_service.models.Order;
import com.ecomm.project.order_service.proxy.ProductServiceProxy;
import com.ecomm.project.order_service.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RefreshScope
@RestController
public class OrderController {

    @Value("${user.count}")
    String count;

    @Autowired
    private ProductServiceProxy productProxy;

    @Autowired
    private OrderEventPublisher orderEventPublisher;

    @Autowired
    private OrderService orderService;

    @GetMapping("/orders/list")
    public ResponseEntity<String> getOrders() {
        String products = productProxy.getProducts().getBody();
        String orders = "This is from order service count - "+ count;
        return ResponseEntity.ok(orders + "\n" + products);
    }

    @GetMapping("/orders/publish")
    public ResponseEntity<String> publishOrder() {
        orderEventPublisher.publishOrderEvent("{'name': 'test'}");
        return ResponseEntity.ok("Done!!!!");
    }

    @PostMapping("/orders/placeorder")
    public Order placeOrder(@RequestBody Order order) {
        return orderService.placeOrder(order);
    }

    @GetMapping("/orders/allorders")
    public List<Order> getAllOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/orders/{userId}")
    public List<Order> getOrdersByUser(@PathVariable String userId) {
        return orderService.getOrdersByUser(userId);
    }
}
