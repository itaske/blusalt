package com.blusalt.billerservice.controller;

import com.blusalt.billerservice.dto.PaymentRequest;
import com.blusalt.billerservice.entities.Transaction;
import com.blusalt.billerservice.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/biller-services")
public class BillerController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<?> makePayment(@RequestBody PaymentRequest paymentRequest){
        Transaction transaction = paymentService.makePayment(paymentRequest);
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/{transaction-id}")
    public ResponseEntity<?> getTransaction(@PathVariable("transaction-id") String transactionId){
        return paymentService.getTransaction(transactionId);
    }

}
