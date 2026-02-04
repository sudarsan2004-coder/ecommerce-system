package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.MarriageHall;
import com.example.demo.repository.MarriageHallRepository;
import com.example.demo.service.MarriageHallService;

@Service
public class MarriageHallServiceImpl implements MarriageHallService {

    @Autowired
    private MarriageHallRepository repo;

    @Override
    public MarriageHall addHall(MarriageHall hall) {
        hall.setStatus("ACTIVE");
        return repo.save(hall);
    }

    @Override
    public List<MarriageHall> getActiveHalls() {
        return repo.findByStatus("ACTIVE");
    }
    
    @Override
    public MarriageHall getById(int id) {
        return repo.findById(id).orElse(null);
    }
}
