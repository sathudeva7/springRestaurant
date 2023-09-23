package dev.sathu.movies.model;

import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class Output {
    private ObjectId id;
    private ObjectId userId;
    private List<OrderItem> orderItems;
    private Boolean delivered;
    private double amount;

   private String userName;

   public Output() {

   }

    public Output(ObjectId id, ObjectId userId, List<OrderItem> orderItems, Boolean delivered, double amount,String userName) {
        this.id = id;
        this.userId = userId;
        this.orderItems = orderItems;
        this.delivered = delivered;
        this.amount = amount;
        this.userName = userName;
    }

    public ObjectId getId() {
        return id;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public Boolean getDelivered() {
        return delivered;
    }

    public double getAmount() {
        return amount;
    }

    public String getUserName() {
        return userName;
    }
}
