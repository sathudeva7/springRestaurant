package dev.sathu.movies.controller;

import dev.sathu.movies.model.Menu;
import org.bson.types.ObjectId;

import java.util.List;

public class OrderPayload {
    private ObjectId userId;
    private ObjectId restaurantId;
    private List<Menu> menuIds;

    private double amount;

    public OrderPayload(ObjectId userId, ObjectId restaurantId, List<Menu> menuIds, double amount) {
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

    public List<Menu> getMenuIds() {
        return menuIds;
    }

    public double getAmount() {
        return amount;
    }
}
