package dev.sathu.movies.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.Date;

@Document(collection = "review")
@Data
@AllArgsConstructor
public class Review {

    @Id
    private ObjectId id;

    private String content;

    private String userId;

    private String userName;

    private String restaurantId;

    private String stars;

    private Date createdAt;

    public Review() {
    }

    public Review( String content, String userId, String userName, String restaurantId, String stars, Date createdAt) {
        this.content = content;
        this.userId = userId;
        this.userName = userName;
        this.restaurantId = restaurantId;
        this.stars = stars;
        this.createdAt = createdAt;
    }
}
