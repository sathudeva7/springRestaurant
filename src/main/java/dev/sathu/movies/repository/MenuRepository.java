package dev.sathu.movies.repository;

import dev.sathu.movies.model.Menu;
import dev.sathu.movies.model.Restaurant;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MenuRepository extends MongoRepository<Menu, ObjectId> {
    Optional<Menu> findMenuById(ObjectId Id);
}
