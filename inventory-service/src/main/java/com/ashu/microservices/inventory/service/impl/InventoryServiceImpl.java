package com.ashu.microservices.inventory.service.impl;

import com.ashu.microservices.inventory.repository.InventoryRepository;
import com.ashu.microservices.inventory.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class InventoryServiceImpl implements InventoryService {

    @Autowired
    InventoryRepository inventoryRepo;

    @Override
    public boolean checkInventory(String skuCode, Integer quantity) {
        log.info(">>> Service Start - Checking inventory for skuCode: {} and quantity: {}", skuCode, quantity);
        return inventoryRepo.existsBySkuCodeAndQuantityGreaterThanEqual(skuCode, quantity);
    }
}
