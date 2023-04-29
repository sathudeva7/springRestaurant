package dev.sathu.movies.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "user")
@Data
@AllArgsConstructor
public class User {
    @Id
    private ObjectId id;
    private String name;
    private String email;
    private String phoneNumber;
    private String token;
    private Boolean isVerified = false;
    private Address address;
    public User() {

    }


    public User(String name, String email, String phoneNumber, String token) {
        this.name = name;
        this.token = token;
        this.email = email;
        this.phoneNumber = phoneNumber;

    }


}
