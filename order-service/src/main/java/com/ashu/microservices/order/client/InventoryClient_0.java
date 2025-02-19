//package com.ashu.microservices.order.client;
//
//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
//import io.github.resilience4j.retry.annotation.Retry;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.service.annotation.GetExchange;
//
////@FeignClient(name = "inventory", url = "http://localhost:8082")
//
//public interface InventoryClient_0 {
//
//    Logger log = LoggerFactory.getLogger(InventoryClient_0.class);
//
////    @GetMapping("/api/inventory")
//    @GetExchange("/api/inventory")
//    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
//    @Retry(name = "inventory")
//    boolean checkInventory(@RequestParam String skuCode, @RequestParam Integer quantity);
//
//    default boolean fallbackMethod(String skuCode, Integer quantity, Throwable throwable) {
//        log.info("Cannot get inventory for skuCode: {}, failure reason: {}", skuCode, throwable.getMessage());
//        return false;
//    }
//}
