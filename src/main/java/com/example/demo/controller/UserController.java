package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.User;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // Login page
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    // Home / index page
    @GetMapping("/index")
    public String indexPage() {
        return "index";
    }

    // Login action
    @PostMapping("/login")
    public String login(User user, HttpSession session, Model model) {

        User u = userService.login(user.getMobile(), user.getPassword());

        if (u != null) {
            session.setAttribute("user", u);
            return "redirect:/dashboard";
        } else {
            model.addAttribute("error", "Invalid mobile number or password");
            return "login";
        }
    }

    // Register page
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    // Register save with duplicate check
    @PostMapping("/register")
    public String register(User user, Model model) {

        // ✅ CHECK DUPLICATE MOBILE
        if (userService.existsByMobile(user.getMobile())) {
            model.addAttribute("error", 
                "Mobile number already registered. Please login.");
            return "register";
        }

        userService.register(user);
        return "redirect:/login";
    }

    // Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
