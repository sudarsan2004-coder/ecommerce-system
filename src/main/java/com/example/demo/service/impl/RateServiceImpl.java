package com.example.demo.service.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.DailyRate;
import com.example.demo.repository.RateRepository;
import com.example.demo.service.RateService;

@Service
public class RateServiceImpl implements RateService {

    @Autowired
    private RateRepository repo;

    @Override
    public DailyRate setTodayRate(DailyRate rate) {
        rate.setRateDate(LocalDate.now());
        return repo.save(rate);
    }

    @Override
    public DailyRate getTodayRate() {
        return repo.findByRateDate(LocalDate.now());
    }
}
