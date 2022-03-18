package com.example.demo.controller.view;

import com.example.demo.sevice.place.PlaceService;
import com.example.demo.sevice.user.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
@RequiredArgsConstructor
public class AdminViewController {
    private final AccountService accountService;
    private final PlaceService placeService;

    @RequestMapping("user")
    public String user() {
        return "admin-user";
    }

    @RequestMapping("user/edit/{id}")
    public String userEdit(@PathVariable Long id, Model model) {
        model.addAttribute("user", accountService.findById(id).getBody().getResult());
        return "user-edit";
    }

    @RequestMapping("user/save")
    public String userSave() {
        return "user-save";
    }

    @RequestMapping("place")
    public String place() {
        return "admin-place";
    }

    @RequestMapping("place/edit/{id}")
    public String placeEdit(@PathVariable Long id, Model model) {
        model.addAttribute("place", placeService.findById(id).getBody().getResult());
        return "place-edit";
    }

    @RequestMapping("place/save")
    public String placeSave() {
        return "place-save";
    }
}
