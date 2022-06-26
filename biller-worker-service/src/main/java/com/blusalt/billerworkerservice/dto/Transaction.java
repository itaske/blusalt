package com.blusalt.billerworkerservice.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Transaction {

    private String id;

    private BigDecimal amount;

    private String customerId;

    private Status status;

    public enum Status{
        PENDING,
        SUCCESS,
        FAILURE
    }

}
