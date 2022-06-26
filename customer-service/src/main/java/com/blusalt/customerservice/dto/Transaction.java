package com.blusalt.customerservice.dto;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
