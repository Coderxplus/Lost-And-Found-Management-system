package com.example.lostfoundMS.controllers;

import com.example.lostfoundMS.entities.Item;
import com.example.lostfoundMS.services.ItemService;
import com.example.lostfoundMS.services.UserService;
import com.example.lostfoundMS.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private AuthUtils authUtils;

    @GetMapping("/")
    public String home(Model model) {
        authUtils.addAuthAttributes(model);
        model.addAttribute("items", itemService.getAllItems());
        return "index";
    }
    @GetMapping("/report")
    public String showReport(Model model){
        model.addAttribute("item", new Item());
        authUtils.addAuthAttributes(model);
        return "report";
    }
    @GetMapping("/dashBoard")
    public String showDashBoard(Model model){
        authUtils.addAuthAttributes(model);
        return "dashboard";
    }
    @PostMapping("/report")
    public String postReport(@ModelAttribute Item item, @RequestParam String type, Model model){
        try{
            String email = authUtils.getCurrentUser().getEmail();
            if (type.equals("LOST")) {
                itemService.reportLostItem(item, email);
            } else {
                itemService.reportFoundItem(item, email);
            }

            return "redirect:/dashboard";
        }
        catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("item", item);
            authUtils.addAuthAttributes(model);
            return "report";
        }
    }
}
