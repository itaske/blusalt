package com.blusalt.billerservice.entities;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class Transaction {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private BigDecimal amount;

    private String customerId;

    @Enumerated(value = EnumType.STRING)
    private Status status;


    public enum Status{
        PENDING,
        SUCCESS,
        FAILURE
    }

}
