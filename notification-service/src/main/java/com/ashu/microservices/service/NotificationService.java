package com.ashu.microservices.service;

import com.ashu.microservices.order.event.OrderPlacedEvent;
import com.ashu.microservices.utility.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {

    @Autowired
    EmailService emailService;

    @KafkaListener(topics = "order-placed", groupId = "notification")
    public void listen(OrderPlacedEvent orderPlacedEvent) {
        log.info("Notification needs to be sent for order number {} to email {}",
                orderPlacedEvent.getOrderNumber(), orderPlacedEvent.getEmail());

        //send email

        String subject = "Your order with order number: " + orderPlacedEvent.getOrderNumber() +
                "has been placed successfully";

        String body = String.format("""
                Dear Customer,
                Your order with order number %s has been placed successfully.
                Thank you for shopping with us.
                
                Best Regards,
                Ashu's Shop
                """, orderPlacedEvent.getOrderNumber());

        emailService.sendEmail(orderPlacedEvent.getEmail(),subject,body);


        log.info("Notification for order number {} to email {} sent successfully",
                orderPlacedEvent.getOrderNumber(), orderPlacedEvent.getEmail());


    }


}
