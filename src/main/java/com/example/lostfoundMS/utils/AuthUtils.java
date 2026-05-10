package com.example.lostfoundMS.utils;

import com.example.lostfoundMS.entities.User;
import com.example.lostfoundMS.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

@Component
public class AuthUtils {
    @Autowired
    private UserRepository userRepository;

    public User getCurrentUser(){
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        return userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("User not found"));
    }

    public boolean isLoggedIn(){
        var auth = SecurityContextHolder.getContext()
                .getAuthentication();
        return auth != null && auth.isAuthenticated() && !auth.getName().equals("anonymousUser");
    }
    public boolean isAdmin(){
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }

    // add this to AuthUtils
    public void addAuthAttributes(Model model) {
        try {
            if (isLoggedIn()) {
                model.addAttribute("isLoggedIn", true);
                model.addAttribute("fullName",
                        getCurrentUser().getFullName());
            } else {
                model.addAttribute("isLoggedIn", false);
            }
        } catch (Exception e) {
            model.addAttribute("isLoggedIn", false);
        }
    }
}
