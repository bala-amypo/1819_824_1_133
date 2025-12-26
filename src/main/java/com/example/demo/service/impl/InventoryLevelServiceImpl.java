package com.example.demo.service.implement;

import com.example.demo.entity.InventoryLevel;
import com.example.demo.entity.Product;
import com.example.demo.entity.Store;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.InventoryLevelRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.StoreRepository;
import com.example.demo.service.InventoryLevelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryLevelServiceImpl implements InventoryLevelService {

    private final InventoryLevelRepository inventoryLevelRepository;
    private final StoreRepository storeRepository;
    private final ProductRepository productRepository;

    public InventoryLevelServiceImpl(InventoryLevelRepository inventoryLevelRepository,
                                     StoreRepository storeRepository,
                                     ProductRepository productRepository) {
        this.inventoryLevelRepository = inventoryLevelRepository;
        this.storeRepository = storeRepository;
        this.productRepository = productRepository;
    }

    @Override
    public InventoryLevel createOrUpdateInventory(InventoryLevel inventoryLevel) {
        if (inventoryLevel.getQuantity() == null || inventoryLevel.getQuantity() < 0) {
            // REQUIRED exact phrase:
            throw new BadRequestException("Quantity must be >= 0");
        }

        Store store = inventoryLevel.getStore();
        Product product = inventoryLevel.getProduct();

        if (store == null || store.getId() == null) throw new BadRequestException("not found");
        if (product == null || product.getId() == null) throw new BadRequestException("not found");

        Store storeDb = storeRepository.findById(store.getId())
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        Product productDb = productRepository.findById(product.getId())
                .orElseThrow(() -> new ResourceNotFoundException("not found"));

        return inventoryLevelRepository.findByStoreAndProduct(storeDb, productDb)
                .map(existing -> {
                    existing.setQuantity(inventoryLevel.getQuantity());
                    return inventoryLevelRepository.save(existing);
                })
                .orElseGet(() -> {
                    InventoryLevel nl = new InventoryLevel();
                    nl.setStore(storeDb);
                    nl.setProduct(productDb);
                    nl.setQuantity(inventoryLevel.getQuantity());
                    return inventoryLevelRepository.save(nl);
                });
    }

    @Override
    public List<InventoryLevel> getInventoryForStore(Long storeId) {
        return inventoryLevelRepository.findByStore_Id(storeId);
    }

    @Override
    public List<InventoryLevel> getInventoryForProduct(Long productId) {
        return inventoryLevelRepository.findByProduct_Id(productId);
    }
}