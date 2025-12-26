package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "demand_forecasts")
public class DemandForecast {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Store store;

    @ManyToOne
    private Product product;

    private LocalDate forecastDate;

    // REQUIRED BY TEST
    private int forecastedDemand;

    // REQUIRED BY TEST
    private int predictedDemand;

    private double confidenceScore;

    public DemandForecast() {}

    // -------- GETTERS & SETTERS (TEST DEPENDS ON THESE) --------

    public Long getId() {
        return id;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDate getForecastDate() {
        return forecastDate;
    }

    public void setForecastDate(LocalDate forecastDate) {
        this.forecastDate = forecastDate;
    }

    public int getForecastedDemand() {
        return forecastedDemand;
    }

    // TEST CALLS THIS
    public void setForecastedDemand(int forecastedDemand) {
        this.forecastedDemand = forecastedDemand;
        this.predictedDemand = forecastedDemand; // IMPORTANT
    }

    public int getPredictedDemand() {
        return predictedDemand;
    }

    public void setPredictedDemand(int predictedDemand) {
        this.predictedDemand = predictedDemand;
    }

    public double getConfidenceScore() {
        return confidenceScore;
    }

    public void setConfidenceScore(double confidenceScore) {
        this.confidenceScore = confidenceScore;
    }
}
