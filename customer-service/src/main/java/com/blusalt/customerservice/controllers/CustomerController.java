package com.blusalt.customerservice.controllers;

import com.blusalt.customerservice.dto.PaymentRequest;
import com.blusalt.customerservice.dto.Transaction;
import com.blusalt.customerservice.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/bill")
    public ResponseEntity<?> payBill(@RequestBody PaymentRequest paymentRequest){
        Transaction transaction = paymentService.makePayment(paymentRequest);
        return ResponseEntity.ok(transaction);
    }

    @GetMapping("/transaction/{transaction-id}")
    public ResponseEntity<?> getTransaction(@PathVariable("transaction-id") String transactionId){
        Transaction transaction = paymentService.getCustomerTransaction(transactionId);
        return ResponseEntity.ok(transaction);
    }
}
