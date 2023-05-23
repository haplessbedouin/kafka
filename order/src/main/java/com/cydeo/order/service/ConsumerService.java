package com.cydeo.order.service;

import com.cydeo.order.dto.OrderDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ConsumerService {

    private static final String orderTopic = "${topic.name}";

    private final ObjectMapper objectMapper;

    @Autowired
    public ConsumerService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = orderTopic)
    public void consumeMessage(@Payload String message,
                               @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                               @Header(KafkaHeaders.OFFSET) int offset) throws JsonProcessingException {
        log.info("message consumed {}", message);

        OrderDTO orderDTO = objectMapper.readValue(message, OrderDTO.class);
        System.out.println("order request consumed, order will be created " + orderDTO.getItem());
        System.out.println("PartitionId " +partition);
        System.out.println("Offset " +offset);
    }

}
