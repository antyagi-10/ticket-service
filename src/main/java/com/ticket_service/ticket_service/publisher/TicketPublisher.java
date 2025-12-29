package com.ticket_service.ticket_service.publisher;

import com.ticket_service.ticket_service.payload.TicketPayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class TicketPublisher {

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

//    private static final Logger log = LoggerFactory.getLogger(TicketPublisher.class);

    public TicketPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendEvent(TicketPayload event){
//        log.info(event);
        rabbitTemplate.convertAndSend(exchange, routingKey, event);

    }

}
