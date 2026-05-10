package com.example.lostfoundMS.controllers;

import com.example.lostfoundMS.entities.User;
import com.example.lostfoundMS.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String handleRegister(@ModelAttribute User user, Model model){
        try{
            if(!user.getPassword().equals(user.getConfirmPassword())){
                model.addAttribute("error","Passwords do not match");
                return "register";
            }
            userService.registerUser(user);
            return "redirect:/login";
        }
        catch (RuntimeException e){
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }
}
