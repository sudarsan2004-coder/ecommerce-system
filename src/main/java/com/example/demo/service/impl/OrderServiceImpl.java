package com.example.demo.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.DailyRate;
import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.RateRepository;
import com.example.demo.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private RateRepository rateRepo;

    // USER: place order
    @Override
    public Order placeOrder(Order o) {

        if (o.getDeliveryDate() == null) {
            throw new RuntimeException("Delivery date is required");
        }

        if (o.getDeliveryDate()
                .isBefore(LocalDate.now().plusDays(3))) {
            throw new RuntimeException(
                "Order must be placed at least 3 days in advance");
        }

        DailyRate rate = rateRepo.findByRateDate(LocalDate.now());
        if (rate == null) {
            throw new RuntimeException(
                "Today's rate not set by admin");
        }

        double price =
                o.getOrderType().equals("PIECE")
                ? rate.getPieceRate()
                : rate.getKiloRate();

        o.setRate(price);
        o.setTotal(price * o.getQuantity());
        o.setOrderDate(LocalDate.now());
        o.setStatus("PENDING");

        return orderRepo.save(o);
    }

    // USER: view own orders
    @Override
    public List<Order> getOrdersByUser(int userId) {
        return orderRepo.findByUserId(userId);
    }

   
    
    @Override
    public List<Order> getPendingOrders() {
        return orderRepo.findByStatus("PENDING");
    }

    @Override
    public void updateStatus(int id, String status) {
        Order o = orderRepo.findById(id).orElse(null);
        if (o != null) {
            o.setStatus(status);
            orderRepo.save(o);
        }
    }

}
