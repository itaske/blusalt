package com.blusalt.customerservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentRequest {

    private String customerId;
    private BigDecimal amount;

    @JsonIgnore
    private LocalDateTime dateTransacted;
}
