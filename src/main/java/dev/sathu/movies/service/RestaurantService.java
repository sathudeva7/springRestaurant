package dev.sathu.movies.service;

import dev.sathu.movies.model.Address;
import dev.sathu.movies.model.Cusine;
import dev.sathu.movies.model.PaymentOptions;
import dev.sathu.movies.model.Restaurant;
import dev.sathu.movies.repository.RestaurantRepository;
import dev.sathu.movies.utils.CustomizedResponse;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private StorageService storageService;

    public Map<String, Object> createRestaurant(String userId, String name, Address address, Boolean deliveryOptions, Cusine cusine, String phoneNumber,  List<String> image, PaymentOptions paymentOptions ) {



        Restaurant restaurant = restaurantRepository.insert(new Restaurant(userId,name,address,deliveryOptions,cusine,phoneNumber,image,paymentOptions));

        Map<String, Object> response = CustomizedResponse.buildResponse(restaurant, "success", "Restaurant created successfully.");

        return response;
    }

    public Map<String, Object> getAllRestaurants() {
        try {
            List<Restaurant> restaurants = restaurantRepository.findAll();
            List<Map<String, Object>> simplifiedRestaurants = restaurants.stream()
                    .map(r -> {
                        Map<String, Object> simplifiedRestaurant = new HashMap<>();
                        simplifiedRestaurant.put("name", r.getName());
                        simplifiedRestaurant.put("images", r.getImages());
                        simplifiedRestaurant.put("id", r.getId());
                        return simplifiedRestaurant;
                    })
                    .collect(Collectors.toList());

            Map<String, Object> response = CustomizedResponse.buildResponse(simplifiedRestaurants, "success", "All Restaurants fetched successfully.");

            return response;
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            System.out.println(e);
            errorResponse.put("status", "error");
            errorResponse.put("message", "An error occurred while fetching restaurants.");

            return errorResponse;
        }
    }

    public Map<String, Object> getRestaurantByUserId(String userId) {
        try {
            System.out.println("userId" + userId);
            List<Restaurant> restaurants = restaurantRepository.findRestaurantByUserId(userId);

            Map<String, Object> response = CustomizedResponse.buildResponse(restaurants, "success", "Restaurant fetched by id successfully.");

            return response;
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            System.out.println(e);
            errorResponse.put("status", "error");
            errorResponse.put("message", "An error occurred while fetching restaurants.");

            return errorResponse;
        }
    }


    public Map<String, Object> getRestaurantById(ObjectId id) {
        Restaurant restaurant =  restaurantRepository.findRestaurantById(id);

        Map<String, Object> response = CustomizedResponse.buildResponse(restaurant, "success", "Restaurant fetched by id successfully.");

        return response;
    }

    public Map<String, Object> getRestaurantByLocation(String location) {
        List<Restaurant> restaurantsByLocation =  restaurantRepository.findRestaurantByAddress(location);

        List<Map<String, Object>> result = restaurantsByLocation.stream()
                .map(r -> {
                    Map<String, Object> resultArr = new HashMap<>();
                    resultArr.put("name", r.getName());
                    resultArr.put("id", r.getId());
                    return resultArr;
                })
                .collect(Collectors.toList());

        Map<String, Object> response = CustomizedResponse.buildResponse(result, "success", "Restaurant fetched by location successfully.");

        return response;
    }

    public Map<String, Object> changeRestaurantInfo(ObjectId id, String name, Address address, Boolean deliveryOptions, Cusine cusine, String phoneNumber,  List<String> image, PaymentOptions paymentOptions) {
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

        Restaurant updatedRestaurant = restaurantRepository.findRestaurantById(id);

        Map<String, Object> response = CustomizedResponse.buildResponse(updatedRestaurant, "success", "Restaurant Edited successfully.");

        return response;
    }

    public Map<String, Object> deleteRestaurantById(ObjectId restaurantId) {
        restaurantRepository.deleteById(restaurantId);

        Map<String, Object> response = CustomizedResponse.buildResponse(null, "success", "Restaurant deleted successfully.");

        return response;
    }
}
