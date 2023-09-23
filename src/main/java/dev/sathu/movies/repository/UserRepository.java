package dev.sathu.movies.repository;

import dev.sathu.movies.model.Menu;
import dev.sathu.movies.model.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    @Query(value = "{'email' : ?0}")
    User findUserByEmail(String email);

    User findUserByToken(String token);

    User findUserById(ObjectId id);

}