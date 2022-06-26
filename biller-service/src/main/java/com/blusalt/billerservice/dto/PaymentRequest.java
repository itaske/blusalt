package com.blusalt.billerservice.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentRequest {

    private String customerId;
    private BigDecimal amount;
    private LocalDateTime dateTransacted;
}
