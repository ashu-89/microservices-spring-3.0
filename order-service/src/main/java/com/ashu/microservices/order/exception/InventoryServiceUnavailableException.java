package com.ashu.microservices.order.exception;

public class InventoryServiceUnavailableException extends RuntimeException {

    public InventoryServiceUnavailableException(String message) {
        super(message);
    }
}
