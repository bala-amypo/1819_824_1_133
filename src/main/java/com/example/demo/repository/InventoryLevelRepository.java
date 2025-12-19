package com.example.demo.repository;

import com.example.demo.entity.InventoryLevel;
import com.example.demo.entity.Product;
import com.example.demo.entity.Store;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryLevelRepository extends JpaRepository<InventoryLevel, Long> {

    Optional<InventoryLevel> findByStoreAndProduct(Store store, Product product);

    List<InventoryLevel> findByStore(Store store);

    List<InventoryLevel> findByProduct(Product product);
}
