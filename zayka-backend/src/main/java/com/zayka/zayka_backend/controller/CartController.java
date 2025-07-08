package com.zayka.zayka_backend.controller;
import com.zayka.zayka_backend.model.Food;
import com.zayka.zayka_backend.model.User;
import com.zayka.zayka_backend.repository.FoodRepository;
import com.zayka.zayka_backend.repository.UserRepository;
import com.zayka.zayka_backend.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    private final UserRepository userRepo;
    private final FoodRepository foodRepo;
    private final JwtUtil jwtUtil;
    private String extractEmailFromToken(String authHeader) {
        String token = authHeader.substring(7);
        return jwtUtil.getEmailFromToken(token);
    }
    @PostMapping("/add/{foodId}")
    public String addToCart(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
                            @PathVariable String foodId) {

        String email = extractEmailFromToken(authHeader);
        User user = userRepo.findByEmail(email).orElseThrow();

        if (!user.getCart().contains(foodId)) {
            user.getCart().add(foodId);
            userRepo.save(user);
        }

        return "Item added to cart";
    }

    @DeleteMapping("/remove/{foodId}")
    public String removeFromCart(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader,
                                 @PathVariable String foodId) {

        String email = extractEmailFromToken(authHeader);
        User user = userRepo.findByEmail(email).orElseThrow();

        user.getCart().remove(foodId);
        userRepo.save(user);

        return "Item removed from cart";
    }
    @GetMapping
    public List<Food> viewCart(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        String email = extractEmailFromToken(authHeader);
        User user = userRepo.findByEmail(email).orElseThrow();

        List<Food> cartItems = new ArrayList<>();
        for (String foodId : user.getCart()) {
            foodRepo.findById(foodId).ifPresent(cartItems::add);
        }

        return cartItems;
    }
}
