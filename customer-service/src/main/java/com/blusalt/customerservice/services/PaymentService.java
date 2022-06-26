package com.blusalt.customerservice.services;

import com.blusalt.customerservice.dto.PaymentRequest;
import com.blusalt.customerservice.dto.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
@Slf4j
public class PaymentService {

    @Autowired
    private RestTemplate restTemplate;


    @Value("${api.billerUrl}")
    private String billerUrl;

    public Transaction makePayment(PaymentRequest paymentRequest){
        paymentRequest.setDateTransacted(LocalDateTime.now());
        log.info("Making Payment {}", paymentRequest);
        HttpEntity<PaymentRequest> request = new HttpEntity<>(paymentRequest);
        Transaction transaction = restTemplate.postForObject(billerUrl, request, Transaction.class);

        log.info("Transaction processing {}", transaction);

        return transaction;
    }

    public Transaction getCustomerTransaction(String transactionId){
        log.info("Retrieving Customer Transaction with ID {}", transactionId);
        String url = billerUrl+"/"+transactionId;
        Transaction transaction = restTemplate.getForObject(url, Transaction.class);
        log.info("Transaction Retrieved Successfully {}", transaction);
        return transaction;
    }
}
