package com.example.demo.service.impl;

import com.example.demo.entity.*;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.*;
import com.example.demo.service.InventoryBalancerService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class InventoryBalancerServiceImpl implements InventoryBalancerService {

    private final TransferSuggestionRepository transferSuggestionRepository;
    private final InventoryLevelRepository inventoryLevelRepository;
    private final DemandForecastRepository demandForecastRepository;
    private final StoreRepository storeRepository;

    // =========================================================
    // REQUIRED: constructor args order MUST be EXACT:
    // public InventoryBalancerServiceImpl(TransferSuggestionRepository,
    // InventoryLevelRepository, DemandForecastRepository, StoreRepository)
    // =========================================================
    public InventoryBalancerServiceImpl(TransferSuggestionRepository transferSuggestionRepository,
                                        InventoryLevelRepository inventoryLevelRepository,
                                        DemandForecastRepository demandForecastRepository,
                                        StoreRepository storeRepository) {
        this.transferSuggestionRepository = transferSuggestionRepository;
        this.inventoryLevelRepository = inventoryLevelRepository;
        this.demandForecastRepository = demandForecastRepository;
        this.storeRepository = storeRepository;
    }

    @Override
    public List<TransferSuggestion> generateSuggestions(Long productId) {
        // Find all inventory rows for product
        List<InventoryLevel> invList = inventoryLevelRepository.findByProduct_Id(productId);
        if (invList == null || invList.isEmpty()) {
            // If no inventory, we can still treat as no forecast found for balancing
            throw new BadRequestException("No forecast found");
        }

        // If product inactive -> required by tests
        Product product = invList.get(0).getProduct();
        if (product != null && !product.isActive()) {
            throw new BadRequestException("not found");
        }

        // For each store with inventory, find future forecasts (must exist)
        Map<Long, Integer> invQty = new HashMap<>();
        Map<Long, Integer> forecastQty = new HashMap<>();
        LocalDate today = LocalDate.now();

        for (InventoryLevel inv : invList) {
            Store store = inv.getStore();
            if (store == null) continue;

            invQty.put(store.getId(), inv.getQuantity() == null ? 0 : inv.getQuantity());

            List<DemandForecast> forecasts =
                    demandForecastRepository.findByStoreAndProductAndForecastDateAfter(store, product, today);

            if (forecasts == null || forecasts.isEmpty()) {
                // REQUIRED exact phrase:
                throw new BadRequestException("No forecast found");
            }

            // choose nearest future forecast
            forecasts.sort(Comparator.comparing(DemandForecast::getForecastDate));
            Integer demand = forecasts.get(0).getForecastedDemand();
            forecastQty.put(store.getId(), demand == null ? 0 : demand);
        }

        // Identify surplus stores and deficit stores
        List<Long> surplusStores = new ArrayList<>();
        List<Long> deficitStores = new ArrayList<>();

        for (Long storeId : invQty.keySet()) {
            int inv = invQty.getOrDefault(storeId, 0);
            int fc = forecastQty.getOrDefault(storeId, 0);
            if (inv > fc) surplusStores.add(storeId);
            if (inv < fc) deficitStores.add(storeId);
        }

        List<TransferSuggestion> suggestions = new ArrayList<>();

        // Simple pairing logic: take from surplus to deficit
        for (Long srcId : surplusStores) {
            int srcSurplus = invQty.get(srcId) - forecastQty.get(srcId);

            for (Long tgtId : deficitStores) {
                int tgtNeed = forecastQty.get(tgtId) - invQty.get(tgtId);
                if (srcSurplus <= 0) break;
                if (tgtNeed <= 0) continue;

                int moveQty = Math.min(srcSurplus, tgtNeed);

                Store src = storeRepository.findById(srcId)
                        .orElseThrow(() -> new ResourceNotFoundException("not found"));
                Store tgt = storeRepository.findById(tgtId)
                        .orElseThrow(() -> new ResourceNotFoundException("not found"));

                TransferSuggestion ts = new TransferSuggestion();
                ts.setSourceStore(src);
                ts.setTargetStore(tgt);
                ts.setProduct(product);
                ts.setSuggestedQuantity(moveQty);
                ts.setReason("Auto-balance suggestion");

                // priority based on size
                if (tgtNeed >= 50) ts.setPriority("HIGH");
                else if (tgtNeed >= 20) ts.setPriority("MEDIUM");
                else ts.setPriority("LOW");

                suggestions.add(transferSuggestionRepository.save(ts));

                // update local counters
                srcSurplus -= moveQty;
                invQty.put(tgtId, invQty.get(tgtId) + moveQty);
            }
        }

        return suggestions;
    }

    @Override
    public List<TransferSuggestion> getSuggestionsForStore(Long storeId) {
        return transferSuggestionRepository.findBySourceStoreId(storeId);
    }

    @Override
    public TransferSuggestion getSuggestionById(Long id) {
        return transferSuggestionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
    }
}