package com.example.demo.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ReservationViewController {
    @RequestMapping("/reserve/{id}")
    public String save(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "reserve-save";
    }
}
