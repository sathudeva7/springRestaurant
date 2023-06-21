package dev.sathu.movies.service;

import dev.sathu.movies.model.*;
import dev.sathu.movies.repository.MenuRepository;
import dev.sathu.movies.utils.CustomizedResponse;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    public Map<String, Object> createMenu(Menu payload) {
        Menu menu = menuRepository.save(payload);

        Map<String, Object> response = CustomizedResponse.buildResponse(menu, "success", "Menu created successfully.");

        return response;
    }

    public Map<String, Object> getMenuById(ObjectId id) {
        Menu menu =  menuRepository.findMenuById(id);

        Map<String, Object> response = CustomizedResponse.buildResponse(menu, "success", "Menu fetched by id successfully.");

        return response;
    }

    public Map<String, Object> getMenuByCategory(String category) {
        List<Menu> menu =  menuRepository.findMenuByCategory(category);

        Map<String, Object> response = CustomizedResponse.buildResponse(menu, "success", "Menu fetched by category successfully.");

        return response;
    }

    public Map<String, Object> deleteMenuById(ObjectId menuId) {
        menuRepository.deleteById(menuId);

        Map<String, Object> response = CustomizedResponse.buildResponse(null, "success", "Menu deleted successfully.");

        return response;
    }

    public Map<String, Object> editMenuById(ObjectId menuId, Menu menu) {
        Menu oldMenu = menuRepository.findById(menuId).orElseThrow(() -> new Error("Menu not found with id: " ));

        oldMenu.setName(menu.getName());
        oldMenu.setDescription(menu.getDescription());
        oldMenu.setPrice(menu.getPrice());
        oldMenu.setCategory(menu.getCategory());
        oldMenu.setMenuImages(menu.getMenuImages());
        oldMenu.setAvailability(menu.getAvailability());

        menuRepository.save(oldMenu);

        Menu updatedMenu = menuRepository.findMenuById(menuId);

        Map<String, Object> response = CustomizedResponse.buildResponse(updatedMenu, "success", "Menu Edited successfully.");

        return response;
    }

    public Map<String, Object> getMenyByRestaurantId(ObjectId restaurantId) {
        List<Menu> menuByRestaurant =  menuRepository.findMenuByRestaurantId(restaurantId);

        Map<String, Object> response = CustomizedResponse.buildResponse(menuByRestaurant, "success", "Menu menu by restaurant successfully.");

        return response;
    }
}
