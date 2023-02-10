package dev.sathu.movies.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "menu")
@Data
@AllArgsConstructor
public class Menu {
    @Id
    private ObjectId id;
    private String name;
    private String description;
    private Double price;
    private List<String> menuImages;
    private Category category;
    private Boolean availability;
    private ObjectId restaurantId;

    public Menu(String name, String description, Double price, List<String> menuImages, Category category, Boolean availability, ObjectId restaurantId) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.menuImages = menuImages;
        this.category = category;
        this.availability = availability;
        this.restaurantId = restaurantId;
    }

    public Menu() {

    }
}

