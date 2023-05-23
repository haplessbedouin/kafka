package com.cydeo.checkout.service.producer;

import com.cydeo.checkout.dto.OrderDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProducerService {

    @Value("${topic.name}")
    private String orderTopic;

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public ProducerService(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public String sendMessage(OrderDTO orderDTO) throws JsonProcessingException {
        String orderAsMessage = objectMapper.writeValueAsString(orderDTO);
        kafkaTemplate.send(orderTopic, orderAsMessage);

        log.info("checkout success order produced {}", orderAsMessage);

        return "message sent";
    }
}
