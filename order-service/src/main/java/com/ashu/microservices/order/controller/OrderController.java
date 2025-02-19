package com.ashu.microservices.order.controller;

import com.ashu.microservices.order.dto.OrderRequestDTO;
import com.ashu.microservices.order.dto.OrderResponseDTO;
import com.ashu.microservices.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
//    public ResponseEntity<String> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {

//        orderService.createOrder(orderRequestDTO);

//        return new ResponseEntity<>("Order placed successfully", HttpStatus.CREATED);
        log.info(">>> Controller Start - Creating order for skuCode: {} and quantity: {}", orderRequestDTO.skuCode(), orderRequestDTO.quantity());

        return new ResponseEntity<>(orderService.createOrder(orderRequestDTO), HttpStatus.CREATED);

    }
}
