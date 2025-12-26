package com.example.demo.controller;

import com.example.demo.entity.DemandForecast;
import com.example.demo.service.DemandForecastService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/forecasts")
public class DemandForecastController {

    private final DemandForecastService demandForecastService;

    public DemandForecastController(DemandForecastService demandForecastService) {
        this.demandForecastService = demandForecastService;
    }

    @PostMapping
    public DemandForecast create(@RequestBody DemandForecast forecast) {
        return demandForecastService.createForecast(forecast);
    }

    @GetMapping("/store/{storeId}")
    public List<DemandForecast> byStore(@PathVariable Long storeId) {
        return demandForecastService.getForecastsForStore(storeId);
    }
}