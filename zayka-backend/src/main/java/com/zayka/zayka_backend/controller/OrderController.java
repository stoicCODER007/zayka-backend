package com.zayka.zayka_backend.controller;
import com.zayka.zayka_backend.model.Order;
import com.zayka.zayka_backend.model.User;
import com.zayka.zayka_backend.repository.FoodRepository;
import com.zayka.zayka_backend.repository.OrderRepository;
import com.zayka.zayka_backend.repository.UserRepository;
import com.zayka.zayka_backend.security.JwtUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final JwtUtil jwtUtil;
    private final UserRepository userRepo;
    private final FoodRepository foodRepo;
    private final OrderRepository orderRepo;

    private String extractEmail(String authHeader) {
        return jwtUtil.getEmailFromToken(authHeader.substring(7));
    }
    @PostMapping("/place")
    public String placeOrder(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        String email = extractEmail(authHeader);
        User user = userRepo.findByEmail(email).orElseThrow();

        List<String> cart = user.getCart();
        if (cart.isEmpty()) return "Cart is empty!";

        double total = cart.stream()
                .map(foodRepo::findById)
                .filter(Optional::isPresent)
                .mapToDouble(f -> f.get().getPrice())
                .sum();

        Order order = Order.builder()
                .userEmail(email)
                .foodIds(new ArrayList<>(cart))
                .totalAmount(total)
                .orderTime(LocalDateTime.now())
                .build();

        orderRepo.save(order);

        // Clear user's cart
        user.setCart(new ArrayList<>());
        userRepo.save(user);

        return "Order placed successfully!";
    }

    @GetMapping
    public List<Order> getOrders(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        String email = extractEmail(authHeader);
        return orderRepo.findByUserEmail(email);
    }

}
