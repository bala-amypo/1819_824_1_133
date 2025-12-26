package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "demand_forecasts")
public class DemandForecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Store store;

    @ManyToOne(optional = false)
    private Product product;

    @Column(name = "forecast_date", nullable = false)
    private LocalDate forecastDate;

    @Column(name = "forecasted_demand", nullable = false)
    private int forecastedDemand;

    @Column(name = "confidence_score")
    private double confidenceScore;

    // ---------- GETTERS ----------
    public Long getId() { return id; }
    public Store getStore() { return store; }
    public Product getProduct() { return product; }
    public LocalDate getForecastDate() { return forecastDate; }
    public int getForecastedDemand() { return forecastedDemand; }
    public double getConfidenceScore() { return confidenceScore; }

    // ---------- SETTERS (CRITICAL FOR TESTS) ----------
    public void setId(Long id) { this.id = id; }
    public void setStore(Store store) { this.store = store; }
    public void setProduct(Product product) { this.product = product; }
    public void setForecastDate(LocalDate forecastDate) { this.forecastDate = forecastDate; }
    public void setForecastedDemand(int forecastedDemand) { this.forecastedDemand = forecastedDemand; }
    public void setConfidenceScore(double confidenceScore) { this.confidenceScore = confidenceScore; }
}
