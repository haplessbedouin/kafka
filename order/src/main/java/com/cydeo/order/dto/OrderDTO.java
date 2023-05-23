package com.cydeo.order.dto;

import lombok.Data;
import lombok.Value;

@Data
@Value
public class OrderDTO {
    String item;
    Double amount;
}
