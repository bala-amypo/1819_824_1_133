package com.example.demo.service.impl;

import com.example.demo.entity.DemandForecast;
import com.example.demo.repository.DemandForecastRepository;
import com.example.demo.service.DemandForecastService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class DemandForecastServiceImpl implements DemandForecastService {

    private final DemandForecastRepository repository;

    public DemandForecastServiceImpl(DemandForecastRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public DemandForecast createForecast(
            com.example.demo.entity.Store store,
            com.example.demo.entity.Product product,
            LocalDate forecastDate,
            Integer predictedDemand,
            Double confidenceScore) {

        DemandForecast forecast = new DemandForecast();
        forecast.setStore(store);
        forecast.setProduct(product);
        forecast.setForecastDate(forecastDate);
        forecast.setPredictedDemand(predictedDemand); // REQUIRED
        forecast.setConfidenceScore(confidenceScore);

        return repository.save(forecast);
    }

    // ✅ REQUIRED BY INTERFACE (FIX)
    @Override
    @Transactional(readOnly = true)
    public List<DemandForecast> getForecastsForStore(Long storeId) {
        return repository.findByStoreId(storeId);
    }
}
