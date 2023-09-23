package dev.sathu.movies.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "order")
@Data
@AllArgsConstructor
public class Order {
    @Id
    private ObjectId id;
    private ObjectId userId;
    private List<OrderItem> orderItems;
    private Boolean delivered;
    private double amount;

    private Date createdAt;

    public Order() {
        this.orderItems = new ArrayList<>();
        this.delivered = false;
    }

    public Order(ObjectId userId, double amount, Date createdAt) {
        this();
        this.userId = userId;
        this.amount = amount;
        this.createdAt = createdAt;
    }

    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
    }

    public void addOrderItems(List<OrderItem> orderItems) {
        this.orderItems.addAll(orderItems);
    }

    // Other getter and setter methods
}
