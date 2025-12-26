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
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "forecast_date", nullable = false)
    private LocalDate forecastDate;

    @Column(name = "predicted_demand", nullable = false)
    private Integer predictedDemand;

    @Column(name = "confidence_score")
    private Double confidenceScore;

    public Long getId() { return id; }
    public Store getStore() { return store; }
    public Product getProduct() { return product; }
    public LocalDate getForecastDate() { return forecastDate; }
    public Integer getPredictedDemand() { return predictedDemand; }
    public Double getConfidenceScore() { return confidenceScore; }

    public void setId(Long id) { this.id = id; }
    public void setStore(Store store) { this.store = store; }
    public void setProduct(Product product) { this.product = product; }
    public void setForecastDate(LocalDate forecastDate) { this.forecastDate = forecastDate; }
    public void setPredictedDemand(Integer predictedDemand) { this.predictedDemand = predictedDemand; }
    public void setConfidenceScore(Double confidenceScore) { this.confidenceScore = confidenceScore; }
}
