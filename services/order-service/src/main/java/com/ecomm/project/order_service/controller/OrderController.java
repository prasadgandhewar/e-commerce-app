package com.ecomm.project.order_service.controller;

import com.ecomm.project.order_service.proxy.ProductServiceProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RefreshScope
@RestController
public class OrderController {

    @Value("${user.count}")
    String count;

    @Autowired
    private ProductServiceProxy productProxy;

    @GetMapping("/orders/list")
    public ResponseEntity<String> getOrders() {
        String products = productProxy.getProducts().getBody();
        String orders = "This is from order service count - "+ count;
        return ResponseEntity.ok(orders + "\n" + products);
    }
}
