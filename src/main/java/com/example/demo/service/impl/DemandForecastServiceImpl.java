package com.example.demo.service.impl;

import com.example.demo.entity.DemandForecast;
import com.example.demo.repository.DemandForecastRepository;
import com.example.demo.service.DemandForecastService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemandForecastServiceImpl implements DemandForecastService {

    private final DemandForecastRepository repo;

    public DemandForecastServiceImpl(DemandForecastRepository repo) {
        this.repo = repo;
    }

    @Override
    public DemandForecast createForecast(DemandForecast forecast) {
        return repo.save(forecast);
    }

    @Override
    public List<DemandForecast> getForecastsForStore(Long storeId) {
        return repo.findByStoreId(storeId);
    }
}
