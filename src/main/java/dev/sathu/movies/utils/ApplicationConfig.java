package dev.sathu.movies.utils;

import dev.sathu.movies.model.User;
import dev.sathu.movies.repository.UserRepository;
import dev.sathu.movies.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final UserRepository repository;
    private final UserService userService;
    @Autowired
    private MongoTemplate mongoTemplate;

//    private final static List<User> APP_USERS = Arrays.asList(
//            new User("wdeva22@gmail.com", "password", Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))),
//            new User("e15330@gmail.com", "password", Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")))
//    );

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)  throws Exception{
        return config.getAuthenticationManager();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                            System.out.println("posss"+ email);
                            User user = mongoTemplate.findOne(Query.query(Criteria.where("email").is(email)), User.class);
                            System.out.println(user);
                            return user;
            }
//            @Override
//            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//                return APP_USERS
//                        .stream()
//                        .filter(u -> u.getEmail().equals(email))
//                        .findFirst()
//                        .orElseThrow(() -> new UsernameNotFoundException("No user"));
//            }
        };
    }
}
