package com.example.demo.service.implement;

import com.example.demo.entity.DemandForecast;
import com.example.demo.entity.Product;
import com.example.demo.entity.Store;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DemandForecastRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.StoreRepository;
import com.example.demo.service.DemandForecastService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DemandForecastServiceImpl implements DemandForecastService {

    private final DemandForecastRepository demandForecastRepository;
    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;

    public DemandForecastServiceImpl(DemandForecastRepository demandForecastRepository,
                                     StoreRepository storeRepository,
                                     ProductRepository productRepository) {
        this.demandForecastRepository = demandForecastRepository;
        this.storeRepository = storeRepository;
        this.productRepository = productRepository;
    }

    @Override
    public DemandForecast createForecast(DemandForecast forecast) {
        if (forecast.getForecastDate() == null || !forecast.getForecastDate().isAfter(LocalDate.now())) {
            // REQUIRED exact phrase:
            throw new BadRequestException("Forecast date must be in the future");
        }
        if (forecast.getForecastedDemand() == null || forecast.getForecastedDemand() < 0) {
            throw new BadRequestException("Quantity must be >= 0");
        }

        Store store = storeRepository.findById(forecast.getStore().getId())
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        Product product = productRepository.findById(forecast.getProduct().getId())
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        forecast.setStore(store);
        forecast.setProduct(product);
        return demandForecastRepository.save(forecast);
    }

    @Override
    public List<DemandForecast> getForecastsForStore(Long storeId) {
        return demandForecastRepository.findByStore_Id(storeId);
    }
}