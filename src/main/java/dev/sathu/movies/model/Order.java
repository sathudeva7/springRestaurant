package dev.sathu.movies.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "order")
@Data
@AllArgsConstructor
public class Order {
    @Id
    private ObjectId id;
    @DocumentReference
    private ObjectId userId;
    @DocumentReference
    private List<ObjectId> menuIds;
    @DocumentReference
    private ObjectId restaurantId;
    private Boolean delivered;
    private double amount;

    public Order(  ) {

    }


    public Order(ObjectId userId, ObjectId restaurantId, double amount, List<ObjectId> menuIds) {
        this.userId = userId;
        this.restaurantId = restaurantId;
        this.amount = amount;
        this.menuIds = menuIds;
        delivered = false;
    }
}
