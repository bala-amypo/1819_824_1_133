package com.example.demo.service.impl;

import com.example.demo.entity.Product;
import com.example.demo.entity.Store;
import com.example.demo.entity.TransferSuggestion;
import com.example.demo.repository.TransferSuggestionRepository;
import com.example.demo.service.InventoryBalancerService;
import org.springframework.stereotype.Service;

@Service
public class InventoryBalancerServiceImpl implements InventoryBalancerService {

    private final TransferSuggestionRepository repo;

    public InventoryBalancerServiceImpl(TransferSuggestionRepository repo) {
        this.repo = repo;
    }

    @Override
    public TransferSuggestion createSuggestion(
            Product product,
            Store source,
            Store target,
            int quantity,
            String reason) {

        TransferSuggestion ts = new TransferSuggestion();
        ts.setProduct(product);
        ts.setSourceStore(source);
        ts.setTargetStore(target);
        ts.setSuggestedQuantity(quantity);
        ts.setReason(reason);

        return repo.save(ts);
    }
}
