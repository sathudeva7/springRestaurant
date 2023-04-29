package dev.sathu.movies.controller;

import dev.sathu.movies.model.Menu;
import dev.sathu.movies.model.Restaurant;
import dev.sathu.movies.service.RestaurantService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Payload payload) {
        return new ResponseEntity<Restaurant>(restaurantService.createRestaurant( payload.getName(), payload.getAddress(), payload.isDeliveryOptions(), payload.getCusine(),payload.getPhoneNumber(),payload.getImage(),payload.getPaymentOptions() ),HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Restaurant>> getAllRestaurants() {
        return new ResponseEntity<List<Restaurant>>(restaurantService.getAllRestaurants(), HttpStatus.OK);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Optional<Restaurant>> getRestaurantById(@PathVariable ObjectId restaurantId) {
        return new ResponseEntity<Optional<Restaurant>>(restaurantService.getRestaurantById(restaurantId), HttpStatus.OK);
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<List<Restaurant>> getRestaurantByLocation(@PathVariable String location) {
        return new ResponseEntity<List<Restaurant>>(restaurantService.getRestaurantByLocation(location), HttpStatus.OK);
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<Optional> changeRestaurantInfo(@PathVariable ObjectId restaurantId, @RequestBody Payload payload) {
        return new ResponseEntity<Optional>(restaurantService.changeRestaurantInfo(restaurantId, payload.getName(), payload.getAddress(), payload.isDeliveryOptions(), payload.getCusine(),payload.getPhoneNumber(),payload.getImage(),payload.getPaymentOptions()), HttpStatus.OK);
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<String> deleteRestaurantById(@PathVariable ObjectId restaurantId) {
        return new ResponseEntity<String>(String.valueOf(restaurantService.deleteRestaurantById(restaurantId)), HttpStatus.OK);
    }
}

