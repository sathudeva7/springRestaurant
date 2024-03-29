package dev.sathu.movies.controller;

import dev.sathu.movies.service.RestaurantService;
import dev.sathu.movies.service.StorageService;
import dev.sathu.movies.utils.CustomizedResponse;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/restaurant")
@CrossOrigin(origins = "*")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private StorageService storageService;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> upload(@RequestParam("file") MultipartFile file) {
        System.out.println("dasdasd------"+file.getOriginalFilename());
        String uploadImage = null;
        try {
             uploadImage = storageService.uploadImage(file);
            System.out.println(uploadImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Map<String, Object> response = CustomizedResponse.buildResponse(uploadImage,"success", "Restaurant created successfully.");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> createRestaurant(@RequestBody Payload payload) {
        return new ResponseEntity<>(restaurantService.createRestaurant(payload.getUserId(), payload.getName(), payload.getAddress(), payload.isDeliveryOptions(), payload.getCusine(),payload.getPhoneNumber(),payload.getImage(),payload.getPaymentOptions() ),HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllRestaurants() {
        return new ResponseEntity<>(restaurantService.getAllRestaurants(), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<Map<String, Object>> getRestaurantByUserId(@PathVariable String userId) {
        return new ResponseEntity<>(restaurantService.getRestaurantByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Map<String, Object>> getRestaurantById(@PathVariable ObjectId restaurantId) {
        return new ResponseEntity<>(restaurantService.getRestaurantById(restaurantId), HttpStatus.OK);
    }

    @GetMapping("/location/{location}")
    public ResponseEntity<Map<String, Object>> getRestaurantByLocation(@PathVariable String location) {
        return new ResponseEntity<>(restaurantService.getRestaurantByLocation(location), HttpStatus.OK);
    }

    @PutMapping("/{restaurantId}")
    public ResponseEntity<Map<String, Object> >changeRestaurantInfo(@PathVariable ObjectId restaurantId, @RequestBody Payload payload) {
        return new ResponseEntity<>(restaurantService.changeRestaurantInfo(restaurantId, payload.getName(), payload.getAddress(), payload.isDeliveryOptions(), payload.getCusine(),payload.getPhoneNumber(),payload.getImage(),payload.getPaymentOptions()), HttpStatus.OK);
    }

    @DeleteMapping("/{restaurantId}")
    public ResponseEntity<Map<String, Object>> deleteRestaurantById(@PathVariable ObjectId restaurantId) {
        return new ResponseEntity<>(restaurantService.deleteRestaurantById(restaurantId), HttpStatus.OK);
    }
}

