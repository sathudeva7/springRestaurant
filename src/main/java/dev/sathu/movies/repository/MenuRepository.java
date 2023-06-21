package dev.sathu.movies.repository;

import dev.sathu.movies.model.Menu;
import dev.sathu.movies.model.Restaurant;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MenuRepository extends MongoRepository<Menu, ObjectId> {
    Menu findMenuById(ObjectId Id);

    @Query(value = "{'category' : ?0}")
    List<Menu> findMenuByCategory(String category);

    @Query(value = "{'restaurantId' : ?0}")
    List<Menu> findMenuByRestaurantId(ObjectId restaurantId);
}
