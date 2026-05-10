package com.example.lostfoundMS.controllers;

import com.example.lostfoundMS.entities.ClaimStatus;
import com.example.lostfoundMS.entities.Item;
import com.example.lostfoundMS.entities.User;
import com.example.lostfoundMS.services.ClaimService;
import com.example.lostfoundMS.services.ItemService;
import com.example.lostfoundMS.utils.AuthUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private AuthUtils authUtils;

    @GetMapping("/browse")
    public String browseItems(@RequestParam(required = false) String keyword, @RequestParam(required = false) String category, Model model){
        if(keyword != null && !keyword.isEmpty()){
            model.addAttribute("items", itemService.searchFoundItems(keyword));
            model.addAttribute("keyword",  keyword);
        }
        else if(category != null && !category.isEmpty()){
            model.addAttribute("items", itemService.getFoundItemsByCategory(category));
            model.addAttribute("category",  category);
        }
        else {
            model.addAttribute("items", itemService.getAllItems());
        }
        authUtils.addAuthAttributes(model);
        return "browse";
    }

    @GetMapping("/items/{id}")
    public String itemDetail(@PathVariable Long id, Model model){
        Item item = itemService.getItemById(id);
        model.addAttribute("item", item);
        authUtils.addAuthAttributes(model);
        return "item_detail";

    }

//    private final ClaimService claimService;
//
//
//    public ItemController(ItemService itemService, ClaimService claimService) {
//        this.itemService = itemService;
//        this.claimService = claimService;
//    }
//
//    @GetMapping("/item/{id}")
//    public String viewItem(@PathVariable Long id, Model model) {
//
//        Item item = itemService.getItemById(id);
//        model.addAttribute("item", item);
//
//        model.addAttribute("claims",
//                claimService.getClaimsByItemId(id));
//
//        return "item-details";
//    }
//
//    @GetMapping("/add")
//    public String showForm(Model model) {
//        model.addAttribute("item", new Item());
//        return "add-item";
//    }
//
//    @PostMapping("/add")
//    public String addItem(@ModelAttribute Item item) {
////        itemService.saveItem(item);
//        return "redirect:/";
//    }
//    @GetMapping("/claim/{itemId}")
//    public String showClaimForm(@PathVariable Long itemId, Model model) {
//        Item item = itemService.getItemById(itemId);
//        model.addAttribute("item", item);
//        return "claim";
//    }
//
//    @PostMapping("/claim/{itemId}")
//    public String submitClaim(@PathVariable Long itemId,
//                              @RequestParam String message) {
//
//        Item item = itemService.getItemById(itemId);
//
//        // TEMP USER (since no login system yet)
//        User fakeUser = new User();
//        fakeUser.setId(1L); // assume user exists
//
//        claimService.createClaim(message, fakeUser, item);
//
//        return "redirect:/";
//    }
//    @GetMapping("/claim/{claimId}/approve")
//    public String approveClaim(@PathVariable Long claimId,
//                               @RequestParam Long itemId) {
//
//        claimService.updateStatus(claimId, ClaimStatus.APPROVED);
//
//        return "redirect:/item/" + itemId;
//    }
//
//    @GetMapping("/claim/{claimId}/reject")
//    public String rejectClaim(@PathVariable Long claimId,
//                              @RequestParam Long itemId) {
//
//        claimService.updateStatus(claimId, ClaimStatus.REJECTED);
//
//        return "redirect:/item/" + itemId;
//    }

}
