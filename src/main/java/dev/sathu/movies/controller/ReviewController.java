package dev.sathu.movies.controller;

import dev.sathu.movies.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/review")
@CrossOrigin(origins = "*")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Map<String,Object>> createReview(@RequestBody ReviewPayload payload) {
        return new ResponseEntity<>(reviewService.createReview(payload.getUserId(), payload.getContent(),payload.getRestaurantId(),payload.getStars()), HttpStatus.CREATED);
    }

    @GetMapping("/{restaurantId}")
    public ResponseEntity<Map<String, Object>> getReviewByRestaurantId(@PathVariable String restaurantId) {
        return new ResponseEntity<>(reviewService.getReviewByRestaurantId(restaurantId), HttpStatus.OK);
    }

}
