package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.MarriageHall;
import com.example.demo.service.MarriageHallService;

@Controller
@RequestMapping("/admin")
public class MarriageHallController {

    @Autowired
    private MarriageHallService service;

    @GetMapping("/add-hall")
    public String addHallPage(Model model) {
        model.addAttribute("hall", new MarriageHall());
        return "add-hall";
    }

    @PostMapping("/add-hall")
    public String saveHall(MarriageHall hall) {
        hall.setStatus("ACTIVE");   // IMPORTANT
        service.addHall(hall);
        return "redirect:/admin/dashboard";
    }
}
