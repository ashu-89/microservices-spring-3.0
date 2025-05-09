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
//    public void createOrder(OrderRequestDTO orderRequestDTO) {


        if (!inventoryClient.checkInventory(orderRequestDTO.skuCode(), orderRequestDTO.quantity())) {
            log.info(">>> Service Start - Insufficient inventory for skuCode: {} and quantity: {}", orderRequestDTO.skuCode(), orderRequestDTO.quantity());
            throw new RuntimeException("Insufficient inventory");
        } else {
            log.info(">>> Service Start - Creating order for skuCode: {} and quantity: {}", orderRequestDTO.skuCode(), orderRequestDTO.quantity());
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequestDTO.price());
            order.setQuantity(orderRequestDTO.quantity());
            order.setSkuCode(orderRequestDTO.skuCode());

            orderRepository.save(order);

            //Send the message to kafka topic - orderNumber, emailId
            OrderPlacedEvent orderPlacedEvent = new OrderPlacedEvent();
            orderPlacedEvent.setOrderNumber(order.getOrderNumber());
            orderPlacedEvent.setEmail(orderRequestDTO.userDetails().email());
            orderPlacedEvent.setFirstName(orderRequestDTO.userDetails().firstName());
            orderPlacedEvent.setLastName(orderRequestDTO.userDetails().lastName());

//            log.info(">>> Start - Sending order placed event {} to kafka topic order-placed", orderPlacedEvent);
//            kafkaTemplate.send("order-placed", orderPlacedEvent);
//            log.info("<<< End - Order placed event {} sent to kafka topic order-placed", orderPlacedEvent);


            //

//
            try {
                log.info(">>> Service KAFKA Start - Sending order placed event {} to kafka topic order-placed", orderPlacedEvent);
                kafkaTemplate.send("order-placed", orderPlacedEvent)
                        .whenComplete((result, ex) -> {
                            if (ex != null) {
                                log.error("Failed to send message: {}", ex.getMessage(), ex);
                            } else {
                                log.info("Message sent successfully to topic {}: {}",
                                        result.getRecordMetadata().topic(),
                                        result.getProducerRecord());
                            }
                        });
            } catch (Exception e) {
                log.error("Unexpected error while sending Kafka message: {}", e.getMessage(), e);
            }


//

            log.info("<<< Service End - Order created successfully for skuCode: {} and quantity: {}", orderRequestDTO.skuCode(), orderRequestDTO.quantity());
            return new OrderResponseDTO(order.getId(),
                    order.getOrderNumber(), order.getSkuCode(), order.getPrice(), order.getQuantity());

        }
    }
}
