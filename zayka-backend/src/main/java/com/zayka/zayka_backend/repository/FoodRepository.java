package com.zayka.zayka_backend.repository;

import com.zayka.zayka_backend.model.Food;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FoodRepository extends MongoRepository<Food, String> {
}
