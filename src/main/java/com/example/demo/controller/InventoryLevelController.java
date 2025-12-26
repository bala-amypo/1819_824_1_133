package com.example.demo.controller;

import com.example.demo.entity.InventoryLevel;
import com.example.demo.service.InventoryLevelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryLevelController {

    private final InventoryLevelService inventoryLevelService;

    public InventoryLevelController(InventoryLevelService inventoryLevelService) {
        this.inventoryLevelService = inventoryLevelService;
    }

    @PostMapping
    public InventoryLevel upsert(@RequestBody InventoryLevel inventoryLevel) {
        return inventoryLevelService.createOrUpdateInventory(inventoryLevel);
    }

    @GetMapping("/store/{storeId}")
    public List<InventoryLevel> byStore(@PathVariable Long storeId) {
        return inventoryLevelService.getInventoryForStore(storeId);
    }

    @GetMapping("/product/{productId}")
    public List<InventoryLevel> byProduct(@PathVariable Long productId) {
        return inventoryLevelService.getInventoryForProduct(productId);
    }
}