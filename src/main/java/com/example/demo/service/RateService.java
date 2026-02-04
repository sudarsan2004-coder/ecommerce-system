package com.example.demo.service;

import com.example.demo.model.DailyRate;

public interface RateService {
    DailyRate setTodayRate(DailyRate rate);
    DailyRate getTodayRate();
}

