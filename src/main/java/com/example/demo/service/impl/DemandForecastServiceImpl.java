package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.repository.DemandForecastRepository;
import com.example.demo.service.DemandForecastService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class DemandForecastServiceImpl implements DemandForecastService {

    private final DemandForecastRepository repository;

    public DemandForecastServiceImpl(DemandForecastRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public DemandForecast createForecast(
            Store store,
            Product product,
            LocalDate forecastDate,
            Integer predictedDemand,
            Double confidenceScore) {

        DemandForecast forecast = new DemandForecast();
        forecast.setStore(store);
        forecast.setProduct(product);
        forecast.setForecastDate(forecastDate);

        // 🔴 REQUIRED (FIX)
        forecast.setPredictedDemand(predictedDemand);

        forecast.setConfidenceScore(confidenceScore);

        return repository.save(forecast);
    }
}
