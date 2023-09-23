package dev.sathu.movies.service;

import dev.sathu.movies.model.Review;
import dev.sathu.movies.model.User;
import dev.sathu.movies.repository.ReviewRepository;
import dev.sathu.movies.repository.UserRepository;
import dev.sathu.movies.utils.CustomizedResponse;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static dev.sathu.movies.utils.JwtAuthFilter.userData;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private UserRepository userRepository;

    public Map<String, Object> createReview(String userId, String content, String restaurantId, String stars) {
        Date today = new Date();
        User data= userData;
        Review review = reviewRepository.insert(new Review(content,userId, data.getName(),restaurantId,stars, today));

        Map<String, Object> response = CustomizedResponse.buildResponse(review, "success", "Review created Successfully");

        return response;
    }

public Map<String, Object> getReviewByRestaurantId(String restaurantId) {
    try {
        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("user") // Name of the user collection
                .localField("userId") // Field in the review collection
                .foreignField("_id") // Field in the user collection (assuming _id is the user identifier)
                .as("userDetails");

        AggregationOperation matchRestaurantOperation = Aggregation.match(Criteria.where("restaurantId").is(restaurantId));

        Aggregation aggregation = Aggregation.newAggregation(lookupOperation, matchRestaurantOperation);

        List<Review> reviews = mongoTemplate.aggregate(aggregation, "review", Review.class).getMappedResults();

        Map<String, Object> response = CustomizedResponse.buildResponse(reviews, "success", "Reviews fetched by restaurant id successfully.");

        return response;
    } catch (Exception e) {
        Map<String, Object> errorResponse = new HashMap<>();
        System.out.println(e);
        errorResponse.put("status", "error");
        errorResponse.put("message", "An error occurred while fetching reviews.");

        return errorResponse;
    }
}

}
