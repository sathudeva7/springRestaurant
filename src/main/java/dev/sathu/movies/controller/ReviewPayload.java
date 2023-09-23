package dev.sathu.movies.controller;

import org.bson.types.ObjectId;

public class ReviewPayload {

    private String content;

    private String stars;

    private String userId;

    private String restaurantId;

    public ReviewPayload(String content, String stars,String userId, String restaurantId) {
        this.content = content;
        this.stars = stars;
        this.userId = userId;
        this.restaurantId = restaurantId;
    }

    public String getContent() {
        return content;
    }

    public String getStars() {
        return stars;
    }

    public String getUserId() {
        return userId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }
}
