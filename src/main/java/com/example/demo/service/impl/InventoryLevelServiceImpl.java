package com.example.demo.service.impl;

import com.example.demo.entity.InventoryLevel;
import com.example.demo.entity.Product;
import com.example.demo.entity.Store;
import com.example.demo.repository.InventoryLevelRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.StoreRepository;
import com.example.demo.service.InventoryLevelService;
import org.springframework.stereotype.Service;

@Service
public class InventoryLevelServiceImpl implements InventoryLevelService {

    private final InventoryLevelRepository inventoryRepo;
    private final StoreRepository storeRepo;
    private final ProductRepository productRepo;

    public InventoryLevelServiceImpl(
            InventoryLevelRepository inventoryRepo,
            StoreRepository storeRepo,
            ProductRepository productRepo) {
        this.inventoryRepo = inventoryRepo;
        this.storeRepo = storeRepo;
        this.productRepo = productRepo;
    }

    @Override
    public InventoryLevel createOrUpdateInventory(InventoryLevel inv) {

        Store store = storeRepo.findById(inv.getStore().getId())
                .orElseThrow(() -> new RuntimeException("Store not found"));

        Product product = productRepo.findById(inv.getProduct().getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return inventoryRepo.findByStoreAndProduct(store, product)
                .map(existing -> {
                    existing.setQuantity(inv.getQuantity());
                    return inventoryRepo.save(existing);
                })
                .orElseGet(() -> {
                    inv.setStore(store);
                    inv.setProduct(product);
                    return inventoryRepo.save(inv);
                });
    }
}
