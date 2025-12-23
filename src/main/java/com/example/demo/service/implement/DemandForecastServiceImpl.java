package com.example.demo.service.impl;

import com.example.demo.entity.DemandForecast;
import com.example.demo.repository.DemandForecastRepository;
import com.example.demo.service.DemandForecastService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DemandForecastServiceImpl implements DemandForecastService {

    @Autowired
    private DemandForecastRepository obj;

    @Override
    public DemandForecast createForecast(DemandForecast forecast) {
        return obj.save(forecast);
    }

    @Override
    public DemandForecast getForecast(Long storeId, Long productId) {
        return obj.findByStoreIdAndProductId(storeId, productId)
                .orElseThrow(() -> new RuntimeException("Forecast not found"));
    }
}
