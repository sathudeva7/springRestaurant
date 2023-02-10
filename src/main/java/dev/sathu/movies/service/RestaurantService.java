package dev.sathu.movies.service;

import dev.sathu.movies.model.Address;
import dev.sathu.movies.model.Cusine;
import dev.sathu.movies.model.PaymentOptions;
import dev.sathu.movies.model.Restaurant;
import dev.sathu.movies.repository.RestaurantRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Restaurant createRestaurant(String name, Address address, Boolean deliveryOptions, Cusine cusine, String phoneNumber, List<String> image, PaymentOptions paymentOptions ) {
        Restaurant restaurant = restaurantRepository.insert(new Restaurant(name,address,deliveryOptions,cusine,phoneNumber,image,paymentOptions));

        return restaurant;
    }

    public  List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Optional<Restaurant> getRestaurantById(ObjectId id) {
        return restaurantRepository.findRestaurantById(id);
    }

    public List<Restaurant> getRestaurantByLocation(String location) {
        return restaurantRepository.findRestaurantByAddress(location);
    }

    public Optional<Restaurant> changeRestaurantInfo(ObjectId id, String name, Address address, Boolean deliveryOptions, Cusine cusine, String phoneNumber, List<String> image, PaymentOptions paymentOptions) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));

        Update update = new Update();
        update.set("name", name);
        update.set("address", address);
        update.set("deliveryOptions", deliveryOptions);
        update.set("cusine", cusine);
        update.set("phoneNumber", phoneNumber);
        update.set("image", image);
        update.set("paymentOptions",paymentOptions);

        mongoTemplate.findAndModify(query, update, Restaurant.class);

        return restaurantRepository.findRestaurantById(id);
    }

    public String deleteRestaurantById(ObjectId restaurantId) {
        restaurantRepository.deleteById(restaurantId);

        return "Restaurant Deleted";
    }
}
