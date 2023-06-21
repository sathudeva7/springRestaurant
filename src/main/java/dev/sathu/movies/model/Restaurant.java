package dev.sathu.movies.model;

import dev.sathu.movies.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "restaurant")
@Data
@AllArgsConstructor
public class Restaurant {
    @Id
    private ObjectId id;
    private String name;
    private Address address;
    private Boolean deliveryOptions;
    private Cusine cusine;
    private String phoneNumber;
    private List<String> images;
    private PaymentOptions paymentOptions;
    @DocumentReference
    private List<Review> reviewIds;

    public Restaurant() {

    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Restaurant(String name, Address address, Boolean deliveryOptions, Cusine cusine, String phoneNumber, List<String> image, PaymentOptions  paymentOptions) {
        this.name = name;
        this.address = address;
        this.deliveryOptions = deliveryOptions;
        this.cusine = cusine;
        this.phoneNumber = phoneNumber;
        this.images = image;
        this.paymentOptions = paymentOptions;
    }

}

