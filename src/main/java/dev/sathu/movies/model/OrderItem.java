package dev.sathu.movies.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.List;

public class OrderItem {
    private ObjectId menuId;        // The ID of the menu item
    private ObjectId restaurantId;  // The ID of the restaurant where the menu item belongs
    private int count;           // The quantity of this menu item in the order

    private String menuName;

    private List<String> menuImage;

    private double menuAmount;

    private boolean delivered;

    // Constructors, getters, and setters
    public ObjectId getMenuId() {
        return menuId;
    }

    public ObjectId getRestaurantId() {
        return restaurantId;
    }

    public int getCount() {
        return count;
    }

    public String getMenuName() {
        return menuName;
    }

    public List<String> getMenuImage() {
        return menuImage;
    }

    public double getMenuAmount() {
        return menuAmount;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public OrderItem(ObjectId menuId, ObjectId restaurantId, int count, String menuName, List<String> menuImage, double menuAmount) {
        this.menuId = menuId;
        this.restaurantId = restaurantId;
        this.count = count;
        this.menuName = menuName;
        this.menuImage = menuImage;
        this.menuAmount = menuAmount;
        this.delivered = false;
    }
}
