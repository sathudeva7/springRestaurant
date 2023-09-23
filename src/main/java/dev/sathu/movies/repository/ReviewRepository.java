package dev.sathu.movies.repository;

import dev.sathu.movies.model.Review;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<Review, ObjectId> {

    @Query(value = "{'restaurantId': ?0}" )
    List<Review> findReviewByRestaurantId(String restaurantId);
}
