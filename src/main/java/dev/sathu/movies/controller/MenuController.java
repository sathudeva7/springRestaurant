package dev.sathu.movies.controller;

import dev.sathu.movies.model.Menu;
import dev.sathu.movies.repository.MenuRepository;
import dev.sathu.movies.service.MenuService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @Autowired
    private MenuRepository menuRepository;

    @PostMapping
    public ResponseEntity<Map<String, Object>>  createMenu(@RequestBody Menu payload) {
        return new ResponseEntity<>(menuService.createMenu(payload), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Menu>> getAllMenu() {
        return new ResponseEntity<List<Menu>>(menuRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{menuId}")
    public ResponseEntity<Map<String, Object>> getMenuById(@PathVariable ObjectId menuId) throws Exception {
        return new ResponseEntity<>(menuService.getMenuById(menuId), HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<Map<String, Object>> getMenuByCategory(@PathVariable String category) {
        return new ResponseEntity<>(menuService.getMenuByCategory(category), HttpStatus.OK);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<Map<String, Object>> getMenyByRestaurantId(@PathVariable ObjectId restaurantId) {
        return new ResponseEntity<>(menuService.getMenyByRestaurantId(restaurantId), HttpStatus.OK);
    }

    @PutMapping("/edit/{menuId}")
    public ResponseEntity<Map<String, Object>> editMenuById(@PathVariable ObjectId menuId, @RequestBody Menu menu) {
        return new ResponseEntity<>(menuService.editMenuById(menuId, menu), HttpStatus.OK);
    }

    @DeleteMapping("/{menuId}")
    public ResponseEntity<Map<String, Object>> deleteMenuById(@PathVariable ObjectId menuId) {
        return new ResponseEntity<>(menuService.deleteMenuById(menuId), HttpStatus.OK);
    }
}
