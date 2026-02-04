package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.model.MarriageHall;
import com.example.demo.model.Order;
import com.example.demo.model.User;
import com.example.demo.service.MarriageHallService;
import com.example.demo.service.OrderService;
import com.example.demo.service.RateService;

import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MarriageHallService hallService;

    @Autowired
    private RateService rateService;

    // ===================== ORDER PAGE =====================
    @GetMapping("/order/{hallId:\\d+}")
    public String orderPage(@PathVariable int hallId,
                            HttpSession session,
                            Model model) {

        // Login check
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }

        // Fetch hall
        MarriageHall hall = hallService.getById(hallId);
        if (hall == null) {
            return "redirect:/";
        }

        // Fetch today rate safely
        var todayRate = rateService.getTodayRate();

        model.addAttribute("hall", hall);
        model.addAttribute("rate",
                todayRate != null ? todayRate.getPieceRate() : 0);

        return "order";
    }

    // ===================== SAVE ORDER =====================
  
    
    @PostMapping("/order/save")
    public String saveOrder(
            Order order,
            Model model,
            HttpSession session
    ) {
        LocalDate minDate = LocalDate.now().plusDays(3);

        if (order.getDeliveryDate().isBefore(minDate)) {

            // 🔁 RELOAD required data for order.html
            MarriageHall hall = hallService.getById(order.getHallId());
            double rate = rateService.getTodayRate().getPieceRate();

            model.addAttribute("hall", hall);
            model.addAttribute("rate", rate);
            model.addAttribute("error",
                    "Delivery date must be at least 3 days from today");

            return "order"; // SAFE now ✅
        }

        User user = (User) session.getAttribute("user");
        order.setUserId(user.getId());

        orderService.placeOrder(order);
        return "redirect:/my-orders";
    }


    // ===================== MY ORDERS =====================
    @GetMapping("/my-orders")
    public String myOrders(HttpSession session, Model model) {

        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        List<Order> orders = orderService.getOrdersByUser(user.getId());
        model.addAttribute("orders", orders);

        return "my-orders";
    }
}
