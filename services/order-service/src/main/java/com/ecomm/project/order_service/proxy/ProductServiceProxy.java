package com.ecomm.project.order_service.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "product-service")
public interface ProductServiceProxy {

    @GetMapping("/products/list")
    public ResponseEntity<String> getProducts();
}
