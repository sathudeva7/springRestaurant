package dev.sathu.movies.repository;

import dev.sathu.movies.model.Order;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, ObjectId> {

    @Query(value = "{'userId': ?0")
    List<Order> findOrdersByUserId(ObjectId userId);
}
