package dev.sathu.movies.controller;

import dev.sathu.movies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createUser(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Map<String, String>>(userService.createUser(payload.get("email")), HttpStatus.CREATED);
    }

    @PutMapping("/verifyMail")
    public ResponseEntity<Map<String, String>> validateEmail(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Map<String, String>>(userService.validateEmail(payload.get("email"), payload.get("token")), HttpStatus.CREATED);
    }

    @PutMapping("/updateProfile")
    public ResponseEntity<Map<String, String>> updateUser(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Map<String, String>>(userService.updateUser(payload.get("email"), payload.get("name"), payload.get("phoneNumber")), HttpStatus.CREATED);
    }




}
