package com.example.demo.service.impl;

import com.example.demo.entity.DemandForecast;
import com.example.demo.repository.DemandForecastRepository;
import com.example.demo.service.DemandForecastService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DemandForecastServiceImpl implements DemandForecastService {

    private final DemandForecastRepository repository;

    public DemandForecastServiceImpl(DemandForecastRepository repository) {
        this.repository = repository;
    }

    // ✅ MUST MATCH INTERFACE EXACTLY
    @Override
    @Transactional
    public DemandForecast createForecast(DemandForecast forecast) {
        return repository.save(forecast);
    }

    // ✅ MUST MATCH INTERFACE EXACTLY
    @Override
    @Transactional(readOnly = true)
    public List<DemandForecast> getForecastsForStore(Long storeId) {
        return repository.findByStoreId(storeId);
    }
}
