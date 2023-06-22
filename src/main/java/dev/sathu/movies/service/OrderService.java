package dev.sathu.movies.service;

import dev.sathu.movies.model.Menu;
import dev.sathu.movies.model.Order;
import dev.sathu.movies.repository.OrderRepository;
import dev.sathu.movies.utils.CustomizedResponse;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.reactive.filter.OrderedWebFilter;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public OrderRepository orderRepository;

    public Map<String, Object> createOrder(ObjectId userId, ObjectId restaurantId, double amount, List<ObjectId> menuIds) {
        System.out.println(menuIds);
        Order order = orderRepository.insert(new Order(userId, restaurantId, amount,menuIds));

        Map<String, Object> response = CustomizedResponse.buildResponse(order, "success", "Order created successfully.");

        return response;
    }

    public Map<String, Object> getOrdersByUserId(ObjectId userId) {
        List<Order> ordersByUser = orderRepository.findOrdersByUserId(userId);

        Map<String, Object> response = CustomizedResponse.buildResponse(ordersByUser, "success", "Orders fetched by user successfully.");

        return response;
    }

    public Map<String, Object> getCompletedOrdersByUserId(ObjectId userId) {
        Query query = new Query(Criteria.where("userId").is(userId).and("delivered").is(true));
        List<Order> completedOrders = mongoTemplate.find(query, Order.class);

        Map<String, Object> response = CustomizedResponse.buildResponse(completedOrders, "success", "Completed Orders fetched by user successfully.");

        return response;
    }

    public Map<String, Object> getPendingOrdersByUserId(ObjectId userId) {
        Query query = new Query(Criteria.where("userId").is(userId).and("delivered").is(false));
        List<Order> pendingOrders = mongoTemplate.find(query, Order.class);

        Map<String, Object> response = CustomizedResponse.buildResponse(pendingOrders, "success", "Pending Orders fetched by user successfully.");

        return response;
    }
}
