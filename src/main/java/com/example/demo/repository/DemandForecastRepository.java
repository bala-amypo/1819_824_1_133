package com.example.demo.repository;

import com.example.demo.entity.DemandForecast;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DemandForecastRepository extends JpaRepository<DemandForecast, Long> {

    // REQUIRED BY TEST
    List<DemandForecast> findByProductId(Long productId);

    // REQUIRED BY TEST
    List<DemandForecast> findByStoreId(Long storeId);
}
