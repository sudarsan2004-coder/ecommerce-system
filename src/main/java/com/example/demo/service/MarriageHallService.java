package com.example.demo.service;

import java.util.List;

import com.example.demo.model.MarriageHall;

public interface MarriageHallService {
    MarriageHall addHall(MarriageHall hall);
    List<MarriageHall> getActiveHalls();
    MarriageHall getById(int id);
}
