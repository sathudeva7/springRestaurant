package dev.sathu.movies.service;

import dev.sathu.movies.model.*;
import dev.sathu.movies.repository.OrderRepository;
import dev.sathu.movies.utils.CustomProjectAggregationOperation;
import dev.sathu.movies.utils.CustomizedResponse;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.lookup;

@Service
public class OrderService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    public OrderRepository orderRepository;

    public Map<String, Object> createOrder(ObjectId userId, double amount, List<OrderItem> orders) {
        // Create an Order
        Date today = new Date();
        Order order = new Order(userId, amount, today);

        List<OrderItem> orderItemList = new ArrayList<>();

// Sample loop to add OrderItems (you can customize this loop as needed)
        for (OrderItem orderItem : orders) {
            ObjectId menuId = orderItem.getMenuId();
            ObjectId restaurantId = orderItem.getRestaurantId();
            int count = orderItem.getCount();
            String menuName = orderItem.getMenuName();
            List<String> menuImage = orderItem.getMenuImage();
            double menuAmount = orderItem.getMenuAmount();
            System.out.println(menuId);

            // Create an OrderItem and add it to the 'order' object
            order.addOrderItem(new OrderItem(menuId, restaurantId, count, menuName, menuImage, menuAmount));
        }


        order.addOrderItems(orderItemList);
        System.out.println(order);
// Save the order in your database
      orderRepository.save(order);

        Map<String, Object> response = CustomizedResponse.buildResponse(order, "success", "Order created successfully.");

        return response;
    }

    public Map<String, Object> getOrdersByUserId(ObjectId userId) {

       try {
           LookupOperation lookupOperation = LookupOperation.newLookup()
                   .from("user") // Name of the user collection
                   .localField("userId") // Field in the review collection
                   .foreignField("_id") // Field in the user collection (assuming _id is the user identifier)
                   .as("data");

           AggregationOperation unwindOperation = Aggregation.unwind("data");
           AggregationOperation projectOperation = Aggregation.project()
                   .and("data.name").as("userName")
                   .and("orderItems").as("orderItems")
                   .and("delivered").as("delivered")
                   .and("amount").as("amount")
                   .and("userId").as("userId")
                   .and("createdAt").as("createdAt");

           Aggregation aggregation = Aggregation.newAggregation(lookupOperation, unwindOperation, projectOperation);
           AggregationResults<Output> results = mongoTemplate.aggregate(aggregation, "order", Output.class);

           List<Output> resultList = results.getMappedResults();
           System.out.println("result" + resultList);

           Map<String, Object> response = CustomizedResponse.buildResponse(resultList, "success", "Orders fetched by user successfully.");

           return response;
       } catch (Exception e) {
           Map<String, Object> errorResponse = new HashMap<>();
           System.out.println(e);
           errorResponse.put("status", "error");
           errorResponse.put("message", "An error occurred while fetching orders.");

           return errorResponse;
       }

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

    public Map<String, Object> getOrdersByRestaurantId(ObjectId restaurantId) {

        try {
            AggregationOperation unwindOperation = Aggregation.unwind("orderItems");

            AggregationOperation matchRestaurantOperation = Aggregation.match(Criteria.where("orderItems.restaurantId").is(restaurantId));

            LookupOperation lookupOperation = LookupOperation.newLookup()
                    .from("restaurant") // Name of the user collection
                    .localField("orderItems.restaurantId") // Field in the review collection
                    .foreignField("_id") // Field in the user collection (assuming _id is the user identifier)
                    .as("data");

            LookupOperation lookupOperation2 = LookupOperation.newLookup()
                    .from("user") // Name of the user collection
                    .localField("userId") // Field in the review collection
                    .foreignField("_id") // Field in the user collection (assuming _id is the user identifier)
                    .as("userData");

            AggregationOperation unwindOperation2 = Aggregation.unwind("data");

            AggregationOperation projectOperation = Aggregation.project()
                    .and("data.name").as("restaurantName")
                    .and("orderItems.delivered").as("delivered")
                    .and("orderItems.menuAmount").as("amount")
                    .and("orderItems.menuName").as("menuName")
                    .and("userId").as("userId")
                    .and("userData.name").as("userName")
                    .and("createdAt").as("createdAt");

            Aggregation aggregation = Aggregation.newAggregation(unwindOperation, matchRestaurantOperation, lookupOperation, lookupOperation2,unwindOperation2, projectOperation);
            AggregationResults<Document> results = mongoTemplate.aggregate(aggregation, "order", Document.class);

            List<Document> resultList = results.getMappedResults();
            System.out.println("result" + resultList);

            Map<String, Object> response = CustomizedResponse.buildResponse(resultList, "success", "Orders fetched by user successfully.");

            return response;
        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            System.out.println("ERROR"+e);
            errorResponse.put("status", "error");
            errorResponse.put("message", "An error occurred while fetching orders.");

            return errorResponse;
        }

    }
}
