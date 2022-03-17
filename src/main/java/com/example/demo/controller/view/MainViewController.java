package com.example.demo.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainViewController {

    @RequestMapping("/test")
    public String getMessage(Model model) {
        model.addAttribute("testSTR", "이제부터는 타임리프로 사용하세요!");
        return "testView";
    }

    // Login form
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    // Login form with error
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @RequestMapping("/common")
    public String common() {
        return "common";
    }

    @RequestMapping("/sign")
    public String sign() {
        return "sign";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "admin";
    }
}
