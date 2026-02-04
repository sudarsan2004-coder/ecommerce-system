package com.example.demo.repository;


import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.DailyRate;

public interface RateRepository extends JpaRepository<DailyRate, Integer> {

    DailyRate findByRateDate(LocalDate rateDate);

}
