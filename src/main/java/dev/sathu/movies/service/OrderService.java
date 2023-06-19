package dev.sathu.movies.service;

import dev.sathu.movies.model.Menu;
import dev.sathu.movies.model.Order;
import dev.sathu.movies.repository.OrderRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public OrderRepository orderRepository;

    public Order createOrder(ObjectId userId, ObjectId restaurantId, double amount, List<Menu> menuIds) {
        Order order = orderRepository.insert(new Order(userId, restaurantId, amount,menuIds));

        return order;
    }

    public List<Order> getOrdersByUserId(ObjectId userId) {
        return orderRepository.findOrdersByUserId(userId);
    }
}
