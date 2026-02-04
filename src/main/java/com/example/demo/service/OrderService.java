package com.example.demo.service;


import java.util.List;
import com.example.demo.model.Order;

public interface OrderService {

    Order placeOrder(Order order);

    List<Order> getOrdersByUser(int userId);

    List<Order> getPendingOrders();

    void updateStatus(int id, String status);
}


