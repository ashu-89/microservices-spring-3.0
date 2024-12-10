package com.ashu.microservices.inventory.service;

public interface InventoryService {
    public boolean checkInventory(String skuCode, Integer quantity);
}
