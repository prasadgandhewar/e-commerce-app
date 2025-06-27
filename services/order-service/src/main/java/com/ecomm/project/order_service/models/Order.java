package com.ecomm.project.order_service.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
@Document(collection = "orders")
public class Order {

    @Id
    private String id;
    private String userId;
    private List<String> productIds;
    private double totalAmount;
    private String status;

//    public Order(String id, String userId, List<String> productIds, double totalAmount, String status) {
//        this.id = id;
//        this.userId = userId;
//        this.productIds = productIds;
//        this.totalAmount = totalAmount;
//        this.status = status;
//    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<String> productIds) {
        this.productIds = productIds;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
//    public Order() {}
//
//    public Order(String userId, List<String> productIds, double totalAmount, String status) {
//        this.userId = userId;
//        this.productIds = productIds;
//        this.totalAmount = totalAmount;
//        this.status = status;
//    }

    // Getters and setters
}
