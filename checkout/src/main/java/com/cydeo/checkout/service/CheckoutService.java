package com.cydeo.checkout.service;

import com.cydeo.checkout.dto.CheckoutDTO;
import com.cydeo.checkout.dto.OrderDTO;
import com.cydeo.checkout.service.producer.ProducerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CheckoutService {

    private final ProducerService producerService;

    @Autowired
    public CheckoutService(ProducerService producerService) {
        this.producerService = producerService;
    }

    public String createOrder(CheckoutDTO checkoutDTO) throws JsonProcessingException {

        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setItem(checkoutDTO.getItem());
        orderDTO.setAmount(checkoutDTO.getAmount());
        return producerService.sendMessage(orderDTO);
    }
}
