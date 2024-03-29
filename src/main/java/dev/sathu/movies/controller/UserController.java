package dev.sathu.movies.controller;

import dev.sathu.movies.model.User;
import dev.sathu.movies.service.UserService;
import dev.sathu.movies.utils.BadRequestException;
import dev.sathu.movies.utils.JWTService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserService userService;

    public UserController() {
    }


    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> createUser(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Map<String, String>>(userService.createUser(payload.get("email"), payload.get("role")), HttpStatus.CREATED);
    }

    @PutMapping("/verifyMail")
    public ResponseEntity<Map<String, String>> validateEmail(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Map<String, String>>(userService.validateEmail(payload.get("email"), payload.get("token")), HttpStatus.CREATED);
    }

    @PutMapping("/updateProfile")
    public ResponseEntity<Map<String, String>> updateUser(@RequestBody Map<String, String> payload) {
        return new ResponseEntity<Map<String, String>>(userService.updateUser(payload.get("email"), payload.get("name"), payload.get("phoneNumber"),payload.get("password")), HttpStatus.CREATED);
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<Map<String,Object>> getUserById(@PathVariable ObjectId userId) throws Exception{
        return new ResponseEntity<Map<String,Object>>(userService.getUserById(userId), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody AuthenticationRequest request) throws Exception {
        System.out.println(request.getEmail() + request.getPassword());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch ( BadCredentialsException e) {
            throw new BadRequestException("Invalid user");
        }
            String email = request.getEmail();
            final User user = (User) userDetailsService.loadUserByUsername(email);

            if (user != null) {
                Map<String, Object> data = new HashMap<>();
                data.put("token", jwtService.generateToken(user));
                data.put("data", user);
                data.put("message", "User login successfully");
                data.put("status", "success");
                return ResponseEntity.ok(data);
            }
        Map<String, Object> data = new HashMap<>();
        data.put("token", "");
        data.put("data", "");
        data.put("message", "User login failed");
        data.put("status", "failed");

        return ResponseEntity.status(400).body(data);
    }


}
