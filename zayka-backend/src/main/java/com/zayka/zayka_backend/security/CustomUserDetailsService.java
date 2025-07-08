package com.zayka.zayka_backend.security;

import com.zayka.zayka_backend.model.User; // Your custom User model
import com.zayka.zayka_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Find user by email in the repository
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        // Map your custom User to Spring Security's UserDetails
        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getEmail()) // Ensure getEmail() exists in your User class
                .password(user.getPassword()) // Ensure getPassword() exists in your User class
                .authorities("USER") // Assign roles/authorities
                .build();
    }
}