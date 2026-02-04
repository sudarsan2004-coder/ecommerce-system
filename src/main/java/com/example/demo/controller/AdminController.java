package com.example.demo.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Order;
import com.example.demo.model.Admin;
import com.example.demo.service.AdminService;
import com.example.demo.service.MarriageHallService;
import com.example.demo.service.OrderService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private OrderService orderService;
    
    @Autowired
    private MarriageHallService hallService;


    // Admin login page
    @GetMapping("/login")
    public String loginPage() {
        return "admin-login";
    }

    // Admin login check
    @PostMapping("/login")
    public String login(Admin admin, HttpSession session, Model model) {

        Admin a = adminService.login(admin.getUsername(), admin.getPassword());

        if (a != null) {
            session.setAttribute("admin", a);
            return "redirect:/admin/dashboard";
        } else {
            model.addAttribute("error", "Invalid admin login");
            return "admin-login";
        }
    }

    // Admin dashboard
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {

        if (session.getAttribute("admin") == null) {
            return "redirect:/admin/login";
        }

        model.addAttribute("orders", orderService.getPendingOrders());
        model.addAttribute("halls", hallService.getActiveHalls());

        return "admin-dashboard";
    }


    // Confirm order
    @GetMapping("/confirm/{id}")
    public String confirm(@PathVariable int id) {
        orderService.updateStatus(id, "CONFIRMED");
        return "redirect:/admin/dashboard";
    }

    // Reject order
    @GetMapping("/reject/{id}")
    public String reject(@PathVariable int id) {
        orderService.updateStatus(id, "REJECTED");
        return "redirect:/admin/dashboard";
    }
}
