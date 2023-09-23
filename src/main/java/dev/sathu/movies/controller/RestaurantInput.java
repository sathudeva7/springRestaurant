package dev.sathu.movies.controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class RestaurantInput {

    @NotEmpty
    private String name;
}
