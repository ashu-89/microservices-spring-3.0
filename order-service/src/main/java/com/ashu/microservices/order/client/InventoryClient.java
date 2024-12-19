package com.ashu.microservices.order.client;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

//@FeignClient(name = "inventory", url = "http://localhost:8082")
public interface InventoryClient  {

//    @GetMapping("/api/inventory")
    @GetExchange("/api/inventory")
    boolean checkInventory(@RequestParam String skuCode, @RequestParam Integer quantity);
}
