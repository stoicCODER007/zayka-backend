package com.zayka.zayka_backend.dto;
import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
}
