package dev.sathu.movies.repository;

import dev.sathu.movies.model.Restaurant;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepository extends MongoRepository<Restaurant, ObjectId> {
    Restaurant findRestaurantById(ObjectId Id);

    @Query(value = "{'address.country' : ?0}")
    List<Restaurant> findRestaurantByAddress(String location);

    @Query(value = "{'userId': ?0}")
    List<Restaurant> findRestaurantByUserId(String userId);


}
