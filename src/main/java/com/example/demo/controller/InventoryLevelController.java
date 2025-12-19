package com.example.demo.controller;

import com.example.demo.entity.InventoryLevel;
import com.example.demo.service.InventoryLevelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryLevelController {

    @Autowired
    private InventoryLevelService obj;

    // PUT /api/inventory/update?storeId=1&productId=2&quantity=50
    @PutMapping("/update")
    public InventoryLevel updateInventory(
            @RequestParam Long storeId,
            @RequestParam Long productId,
            @RequestParam Integer quantity) {

        return obj.updateInventory(storeId, productId, quantity);
    }

    // GET /api/inventory/store/1
    @GetMapping("/store/{storeId}")
    public List<InventoryLevel> getInventoryByStore(
            @PathVariable Long storeId) {

        return obj.getInventoryByStore(storeId);
    }
}
