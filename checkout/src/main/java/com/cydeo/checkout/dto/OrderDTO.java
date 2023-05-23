package com.cydeo.checkout.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderDTO {
    private BigDecimal amount;
    private String item;
}
