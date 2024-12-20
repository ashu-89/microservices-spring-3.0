package com.ashu.microservices.order.service.impl;

import com.ashu.microservices.order.client.InventoryClient;


import com.ashu.microservices.order.dto.OrderRequestDTO;
import com.ashu.microservices.order.dto.OrderResponseDTO;
import com.ashu.microservices.order.model.Order;
import com.ashu.microservices.order.repository.OrderRepository;
import com.ashu.microservices.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    InventoryClient inventoryClient;

    @Override
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequestDTO) {

        if (!inventoryClient.checkInventory(orderRequestDTO.skuCode(), orderRequestDTO.quantity())) {
            throw new RuntimeException("Insufficient inventory");
        } else {
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequestDTO.price());
            order.setQuantity(orderRequestDTO.quantity());
            order.setSkuCode(orderRequestDTO.skuCode());

            orderRepository.save(order);

            return new OrderResponseDTO(order.getId(),
                    order.getOrderNumber(), order.getSkuCode(), order.getPrice(), order.getQuantity());

        }
    }
}
