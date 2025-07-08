package com.zayka.zayka_backend.controller;
import com.zayka.zayka_backend.model.Food;
import com.zayka.zayka_backend.repository.FoodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
@RequiredArgsConstructor
public class FoodController {
    private final FoodRepository foodRepo;
    @PostMapping
    public Food addFood(@RequestBody Food food) {
        return foodRepo.save(food);
    }
    @GetMapping
    public List<Food> getAllFoods() {
        return foodRepo.findAll();
    }
}

