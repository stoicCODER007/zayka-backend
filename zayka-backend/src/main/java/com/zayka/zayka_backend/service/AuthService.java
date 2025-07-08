package com.zayka.zayka_backend.service;

import com.zayka.zayka_backend.dto.AuthResponse;
import com.zayka.zayka_backend.dto.LoginRequest;
import com.zayka.zayka_backend.dto.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
