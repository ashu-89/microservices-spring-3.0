package com.ashu.microservices.inventory.controller;

import com.ashu.microservices.inventory.service.InventoryService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/inventory")
@Slf4j
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

//    @Autowired
//    Logger log;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public boolean checkInventory(String skuCode, Integer quantity) {
        log.info(">>> Controller Start - Checking inventory for skuCode: {} and quantity: {}", skuCode, quantity);
        return inventoryService.checkInventory(skuCode, quantity);
    }

}
