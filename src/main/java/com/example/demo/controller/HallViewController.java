package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.service.MarriageHallService;

import org.springframework.ui.Model;

@Controller
public class HallViewController {

    @Autowired
    private MarriageHallService hallService;

    @GetMapping("/halls")
    public String hallsPage(Model model) {
        model.addAttribute("halls", hallService.getActiveHalls());
        return "halls"; // halls.html
    }
}

