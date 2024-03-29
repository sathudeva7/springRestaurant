package dev.sathu.movies.service;

import dev.sathu.movies.controller.Payload;
import dev.sathu.movies.model.Address;
import dev.sathu.movies.model.Menu;
import dev.sathu.movies.model.User;
import dev.sathu.movies.repository.UserRepository;
import dev.sathu.movies.utils.CustomizedResponse;
import dev.sathu.movies.utils.EmailService;
import dev.sathu.movies.utils.TokenGenerator;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;



@Service
public class UserService {


    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserRepository userRepository;
//    @Autowired(required = true)
//    private BCryptPasswordEncoder  bCryptPasswordEncoder;

    public Map<String, String> createUser(String email, String role) {
        User userByMail = findUserByMail(email);
        Map<String, String> response = new HashMap<>();

        if (userByMail == null) {
            String token = TokenGenerator.generateToken();
            emailService.sendToken(email, token);

            User user = userRepository.save(new User("", email,"",token,role));;
            response.put("message", "success");
            return response;
        } else {
            response.put("message", "User exist");
            return response;
        }
    }

    public Map<String, String>  validateEmail(String email,String token) {
        Map<String, String> response = new HashMap<>();

        User user = mongoTemplate.findOne(Query.query(Criteria.where("email").is(email).and("token").is(token)), User.class);
        // If user is null, token is invalid
        if (user == null) {
            response.put("message", "Token wrong");
            return response;
        } else {
            // Set isVerified to true and save the user
            user.setIsVerified(true);
            mongoTemplate.save(user);

            response.put("message", "Success");
            return response;
        }
    }

    public Map<String, String>  updateUser(String email,String name, String phoneNumber, String password) {
        Map<String, String> response = new HashMap<>();
        System.out.println(password);

        User user = mongoTemplate.findOne(Query.query(Criteria.where("email").is(email)), User.class);

        if (user == null) {
            response.put("message", "User Invalid");
            return response;
        } else {
            user.setName(name);
            user.setPhoneNumber(phoneNumber);
            user.setPassword(password);
            System.out.println(user);
            mongoTemplate.save(user);

            response.put("message", "Success");
            return response;
        }
    }


    public User findUserByMail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public Map<String, Object> getUserById(ObjectId id) {
        User user =  userRepository.findUserById(id);

        Map<String, Object> response = CustomizedResponse.buildResponse(user, "success", "User fetched by id successfully.");

        return response;
    }


    public User findUserByToken(String token) {
        return userRepository.findUserByToken(token);
    }

}
