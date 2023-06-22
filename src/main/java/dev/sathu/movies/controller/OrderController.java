package dev.sathu.movies.controller;

import dev.sathu.movies.model.Order;
import dev.sathu.movies.service.OrderService;
import jakarta.validation.Valid;
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
    public ResponseEntity<Map<String, Object>> createOrder(@Valid @RequestBody OrderPayload orderPayload) {
        return new ResponseEntity<>(orderService.createOrder(orderPayload.getUserId(), orderPayload.getRestaurantId(), orderPayload.getAmount(), orderPayload.getMenuIds()), HttpStatus.CREATED);
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<Map<String, Object>> getOrdersByUserId(@PathVariable ObjectId userId) {
        return new ResponseEntity<>(orderService.getOrdersByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/user/completed/{userId}")
    public ResponseEntity<Map<String, Object>> getCompletedOrdersByUserId(@PathVariable ObjectId userId) {
        return new ResponseEntity<>(orderService.getCompletedOrdersByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/user/pending/{userId}")
    public ResponseEntity<Map<String, Object>> getPendingOrdersByUserId(@PathVariable ObjectId userId) {
        return new ResponseEntity<>(orderService.getPendingOrdersByUserId(userId), HttpStatus.OK);
    }
}
