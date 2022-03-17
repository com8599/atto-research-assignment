package com.example.demo.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class AdminViewController {
    @RequestMapping("user")
    public String user() {
        return "admin-user";
    }

    @RequestMapping("place")
    public String place() {
        return "admin-place";
    }
}
