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

    public List<Menu> getMenuByCategory(String category) {
        return menuRepository.findMenuByCategory(category);
    }

    public String deleteMenuById(ObjectId menuId) {
        menuRepository.deleteById(menuId);
        return "Menu Deleted";
    }

    public Menu editMenuById(ObjectId menuId, Menu menu) {
        Menu oldMenu = menuRepository.findById(menuId).orElseThrow(() -> new Error("Menu not found with id: " ));

        oldMenu.setName(menu.getName());
        oldMenu.setDescription(menu.getDescription());
        oldMenu.setPrice(menu.getPrice());
        oldMenu.setCategory(menu.getCategory());
        oldMenu.setMenuImages(menu.getMenuImages());
        oldMenu.setAvailability(menu.getAvailability());

        return menuRepository.save(oldMenu);
    }

    public List<Menu> getMenyByRestaurantId(ObjectId restaurantId) {
        return menuRepository.findMenuByRestaurantId(restaurantId);
    }
}
