package com.ashu.microservices.order.client;

import com.ashu.microservices.order.exception.InventoryServiceUnavailableException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface InventoryClient {

    Logger log = LoggerFactory.getLogger(InventoryClient.class);

    @GetExchange("/api/inventory")
    @CircuitBreaker(name = "inventory2", fallbackMethod = "fallbackMethod")
//    @CircuitBreaker(name = "inventory")
    @Retry(name = "inventory2")
    boolean checkInventory(@RequestParam String skuCode, @RequestParam Integer quantity);

    default boolean fallbackMethod(String skuCode, Integer quantity, Throwable throwable) {
        log.error("Fallback triggered for skuCode: {}, quantity: {}. Reason: {}", skuCode, quantity, throwable.getMessage());
        throw new InventoryServiceUnavailableException("Inventory service is unavailable. Please try again later.");
    }

    default ResponseEntity<String> handleInventoryServiceUnavailableException(InventoryServiceUnavailableException ex) {
        // Log the error and return a 503 Service Unavailable response
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE)
                .body("Inventory Service is down. Please try again later.");
    }
}
