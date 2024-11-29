package com.ashu.microservices.order.service;

import com.ashu.microservices.order.dto.OrderRequestDTO;
import com.ashu.microservices.order.dto.OrderResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

//@Service
public interface OrderService {
    OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO);
}
