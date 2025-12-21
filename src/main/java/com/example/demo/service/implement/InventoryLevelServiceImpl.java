package com.example.demo.service.impl;

import com.example.demo.entity.InventoryLevel;
import com.example.demo.repository.InventoryLevelRepository;
import com.example.demo.service.InventoryLevelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InventoryLevelServiceImpl implements InventoryLevelService {

    @Autowired
    private InventoryLevelRepository obj;

    @Override
    public InventoryLevel updateInventory(Long storeId, Long productId, Integer quantity) {

        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must be >= 0");
        }

        InventoryLevel objInventory =
                obj.findByStoreIdAndProductId(storeId, productId)
                        .orElseThrow(() -> new RuntimeException("Inventory not found"));

        objInventory.setQuantity(quantity);
        return obj.save(objInventory);
    }

    @Override
    public InventoryLevel getInventory(Long storeId, Long productId) {
        return obj.findByStoreIdAndProductId(storeId, productId)
                .orElseThrow(() -> new RuntimeException("Inventory not found"));
    }

    @Override
    public List<InventoryLevel> getInventoryByStore(Long storeId) {
        return obj.findByStoreId(storeId);
    }
}
