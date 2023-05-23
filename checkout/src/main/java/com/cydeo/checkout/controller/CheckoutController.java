package com.cydeo.checkout.controller;

import com.cydeo.checkout.dto.CheckoutDTO;
import com.cydeo.checkout.service.CheckoutService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/checkout")
public class CheckoutController {
    private final CheckoutService checkoutService;

    public CheckoutController(CheckoutService checkoutService) {
        this.checkoutService = checkoutService;
    }

    @PostMapping
    public String checkout(@RequestBody CheckoutDTO checkoutDTO) throws JsonProcessingException {
        {
            checkoutService.createOrder(checkoutDTO);
            return "message sent";
        }
    }
}
