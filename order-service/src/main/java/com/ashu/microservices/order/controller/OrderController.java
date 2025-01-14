package com.ashu.microservices.order.controller;

import com.ashu.microservices.order.dto.OrderRequestDTO;
import com.ashu.microservices.order.dto.OrderResponseDTO;
import com.ashu.microservices.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<OrderResponseDTO> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {
//    public ResponseEntity<String> createOrder(@RequestBody OrderRequestDTO orderRequestDTO) {

//        orderService.createOrder(orderRequestDTO);

//        return new ResponseEntity<>("Order placed successfully", HttpStatus.CREATED);

        return new ResponseEntity<>(orderService.createOrder(orderRequestDTO), HttpStatus.CREATED);

    }
}
