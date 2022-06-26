package com.blusalt.billerworkerservice.services;

import com.blusalt.billerworkerservice.dto.Transaction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PaymentService {

    public static final String CREATED_BILLS = "Created-Bills";

    public static final String PROCESSED_BILLS = "Processed-Bills";
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = CREATED_BILLS)
    public void processPayments(Transaction transaction){
        try {
            log.info("Starting to Process Transaction {}", transaction);
            Thread.sleep(100);
            transaction.setStatus(Transaction.Status.SUCCESS);
            log.info("Transaction {} Processed Successfully ", transaction);
            log.info("Sending Transaction to Queue {}", PROCESSED_BILLS);
            rabbitTemplate.convertAndSend(PROCESSED_BILLS, transaction);
            log.info("Transaction Sent to Queue {} Successfully", transaction);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }catch(Exception amqpException){
            log.info(String.format("Failed to Push Transaction %s to Queue audit", transaction));
        }
    }
}
