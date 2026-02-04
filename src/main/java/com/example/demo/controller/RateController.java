package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.DailyRate;
import com.example.demo.service.RateService;

@Controller
@RequestMapping("/rate")
public class RateController {

    @Autowired
    private RateService rateService;

    @GetMapping("/setrate")
    public String addratePage(Model model) {
        model.addAttribute("rate", new DailyRate());
        return "add-rate";
    }
    
    @PostMapping("/set")
    public String setRate(DailyRate rate) {
        rateService.setTodayRate(rate);
        return "redirect:/admin/dashboard";
    }
}
