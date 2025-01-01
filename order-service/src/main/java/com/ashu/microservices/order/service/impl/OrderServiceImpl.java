package com.ashu.microservices.order.service.impl;

import com.ashu.microservices.order.client.InventoryClient;


import com.ashu.microservices.order.dto.OrderRequestDTO;
import com.ashu.microservices.order.dto.OrderResponseDTO;
import com.ashu.microservices.order.event.OrderPlacedEvent;
import com.ashu.microservices.order.model.Order;
import com.ashu.microservices.order.repository.OrderRepository;
import com.ashu.microservices.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    InventoryClient inventoryClient;

    @Autowired
    KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

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

            //Send the message to kafka topic - orderNumber, emailId
            OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent(order.getOrderNumber(), orderRequestDTO.userDetails().email());
            log.info(">>> Start - Sending order placed event {} to kafka topic order-placed", orderPlacedEvent);
            kafkaTemplate.send("order-placed", orderPlacedEvent);
            log.info("<<< End - Order placed event {} sent to kafka topic order-placed", orderPlacedEvent);


            //

            return new OrderResponseDTO(order.getId(),
                    order.getOrderNumber(), order.getSkuCode(), order.getPrice(), order.getQuantity());

        }
    }
}
