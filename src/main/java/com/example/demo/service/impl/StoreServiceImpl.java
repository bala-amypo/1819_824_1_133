package com.example.demo.service.implement;

import com.example.demo.entity.Store;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.StoreRepository;
import com.example.demo.service.StoreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    public StoreServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Override
    public Store createStore(Store store) {
        if (storeRepository.findByStoreName(store.getStoreName()).isPresent()) {
            // REQUIRED exact phrase:
            throw new BadRequestException("store name already exists");
        }
        if (store.isActive() == false) {
            // allow but keep as is
        }
        return storeRepository.save(store);
    }

    @Override
    public Store getStoreById(Long id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }

    @Override
    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    @Override
    public Store updateStore(Long id, Store update) {
        Store existing = getStoreById(id);

        // allow storeName change BUT must still be unique
        if (update.getStoreName() != null && !update.getStoreName().equals(existing.getStoreName())) {
            if (storeRepository.findByStoreName(update.getStoreName()).isPresent()) {
                throw new BadRequestException("store name already exists");
            }
            existing.setStoreName(update.getStoreName());
        }

        if (update.getAddress() != null) existing.setAddress(update.getAddress());
        if (update.getRegion() != null) existing.setRegion(update.getRegion());
        existing.setActive(update.isActive());

        return storeRepository.save(existing);
    }

    @Override
    public void deactivateStore(Long id) {
        Store store = getStoreById(id);
        store.setActive(false);
        storeRepository.save(store);
    }
}