package com.example.demo.controller;

import com.example.demo.entity.DemandForecast;
import com.example.demo.service.DemandForecastService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/forecasts")
public class DemandForecastController {

    @Autowired
    private DemandForecastService obj;

    @PostMapping
    public DemandForecast createForecast(
            @RequestBody DemandForecast forecast) {

        return obj.createForecast(forecast);
    }

    @GetMapping("/store/{storeId}/product/{productId}")
    public DemandForecast getForecast(
            @PathVariable Long storeId,
            @PathVariable Long productId) {

        return obj.getForecast(storeId, productId);
    }
}
