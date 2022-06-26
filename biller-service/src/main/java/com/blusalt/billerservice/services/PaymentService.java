package com.blusalt.billerservice.services;

import com.blusalt.billerservice.dto.PaymentRequest;
import com.blusalt.billerservice.entities.Transaction;
import com.blusalt.billerservice.repositories.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class PaymentService {

    public static final String CREATED_BILLS = "Created-Bills";
    public static final String PROCESSED_BILLS = "Processed-Bills";

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Transactional
    public Transaction makePayment(PaymentRequest paymentRequest){
        log.info("Making Payment {}", paymentRequest);
        Transaction transaction = new Transaction();
        transaction.setAmount(paymentRequest.getAmount());
        transaction.setCustomerId(paymentRequest.getCustomerId());
        transaction.setStatus(Transaction.Status.PENDING);

        log.info("Saving Transaction {}", transaction);
        Transaction savedTransaction = transactionRepository.save(transaction);
        log.info("Transaction Saved Successfully");

        log.info("Sending Transaction to Queue {}", CREATED_BILLS);
        try{
            rabbitTemplate.convertAndSend(CREATED_BILLS, savedTransaction);
            log.info("Transaction Sent Successfully to Queue {}", CREATED_BILLS);
        }catch(Exception amqpException){
            log.info("Failed to Push Transaction {} to Queue {}", savedTransaction, CREATED_BILLS);
        }

        return savedTransaction;
    }

    public ResponseEntity<?> getTransaction(String transactionId){
        log.info("Retrieving Transaction with ID {}", transactionId);
        Optional<Transaction> optionalTransaction = transactionRepository.findById(transactionId);

        if (!optionalTransaction.isPresent()){
            return ResponseEntity.notFound().build();
        }

        log.info("Retrieved Transaction with ID {} successfully", transactionId);
        log.info("Retrieved Transaction {}", optionalTransaction.get());
        return ResponseEntity.ok(optionalTransaction.get());
    }


    @RabbitListener(queues = PROCESSED_BILLS)
    public void processSuccessfulPayments(Transaction transaction){
        log.info("Receiving Successful Transaction {}", transaction);
        Transaction successFulTransaction = transactionRepository.saveAndFlush(transaction);
        log.info("Successful Transaction Saved {}", successFulTransaction);
    }


}
