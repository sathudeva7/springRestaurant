package dev.sathu.movies.controller;

import dev.sathu.movies.model.Menu;
import jakarta.validation.constraints.DecimalMin;

import jakarta.validation.constraints.NotBlank;
import org.bson.types.ObjectId;

import java.util.List;

public class OrderPayload {
    @NotBlank(message = "User ID cannot be null")
    private ObjectId userId;
    @NotBlank(message = "Restaurant ID cannot be null")
    private ObjectId restaurantId;
    @NotBlank(message = "Menu ID cannot be null")
    private List<ObjectId> menuIds;
    @NotBlank(message = "Amount cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than zero")
    private double amount;

    public OrderPayload(ObjectId userId, ObjectId restaurantId, List<ObjectId> menuIds, double amount) {
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.menuIds = menuIds;
        this.amount = amount;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public ObjectId getRestaurantId() {
        return restaurantId;
    }

    public List<ObjectId> getMenuIds() {
        return menuIds;
    }

    public double getAmount() {
        return amount;
    }
}
