package dev.sathu.movies.service;

import dev.sathu.movies.model.*;
import dev.sathu.movies.repository.MenuRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Menu createMenu(Menu payload) {
        Menu menu = menuRepository.save(payload);
        return menu;
    }

    public Optional<Menu> getMenuById(ObjectId id) {
        return menuRepository.findMenuById(id);
    }

}
