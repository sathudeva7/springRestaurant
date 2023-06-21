package dev.sathu.movies.controller;

import dev.sathu.movies.model.Order;
import dev.sathu.movies.service.OrderService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createOrder(@RequestBody OrderPayload orderPayload) {
        return new ResponseEntity<>(orderService.createOrder(orderPayload.getUserId(), orderPayload.getRestaurantId(), orderPayload.getAmount(), orderPayload.getMenuIds()), HttpStatus.CREATED);
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<Map<String, Object>> getOrdersByUserId(@PathVariable ObjectId userId) {
        return new ResponseEntity<>(orderService.getOrdersByUserId(userId), HttpStatus.OK);
    }
}
