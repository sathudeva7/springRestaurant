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
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @Autowired
    private MenuRepository menuRepository;

    @PostMapping
    public ResponseEntity<Menu>  createMenu(@RequestBody Menu payload) {
        return new ResponseEntity<Menu>(menuService.createMenu(payload), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Menu>> getAllMenu() {
        return new ResponseEntity<List<Menu>>(menuRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{menuId}")
    public ResponseEntity<Optional<Menu>> getMenuById(@PathVariable ObjectId menuId) {
        return new ResponseEntity<Optional<Menu>>(menuService.getMenuById(menuId), HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Menu>> getMenuByCategory(@PathVariable String category) {
        return new ResponseEntity<List<Menu>>(menuService.getMenuByCategory(category), HttpStatus.OK);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<Menu>> getMenyByRestaurantId(@PathVariable ObjectId restaurantId) {
        return new ResponseEntity<List<Menu>>(menuService.getMenyByRestaurantId(restaurantId), HttpStatus.OK);
    }

    @PutMapping("/edit/{menuId}")
    public ResponseEntity<Menu> editMenuById(@PathVariable ObjectId menuId, @RequestBody Menu menu) {
        return new ResponseEntity<Menu>(menuService.editMenuById(menuId, menu), HttpStatus.OK);
    }

    @DeleteMapping("/{menuId}")
    public ResponseEntity<String> deleteMenuById(@PathVariable ObjectId menuId) {
        return new ResponseEntity<String>(menuService.deleteMenuById(menuId), HttpStatus.OK);
    }
}
