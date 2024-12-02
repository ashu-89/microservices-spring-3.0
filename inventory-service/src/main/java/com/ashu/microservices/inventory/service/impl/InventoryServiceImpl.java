package com.ashu.microservices.inventory.service.impl;

import com.ashu.microservices.inventory.repository.InventoryRepository;
import com.ashu.microservices.inventory.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    InventoryRepository inventoryRepo;

    @Override
    public boolean checkInventory(String skuCode, Integer quantity) {
        return inventoryRepo.existsBySkuCodeAndQuantityGreaterThanEqual(skuCode, quantity);
    }
}
