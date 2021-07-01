package com.demo.payment.controllers;

import java.math.BigDecimal;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.models.PaymentAttempt;
import com.demo.payment.dto.PayOrder;
import com.demo.payment.engine.Producer;

/**
 * REST Payment Controller Class.
 */
@RestController
@RequestMapping(value = "/payment")
public class PaymentController {

    private final Producer producer;
    private final Random random = new Random();

    @Autowired PaymentController(Producer producer) {
        this.producer = producer;
    }

    /**
     * Sample Payments Endpoint. DO NOT use this in production, please :).
     * In the real world you are supposed to at least authenticate the request & get the userId, fetch the Order,
     * and get the amount attribute from there.
     * This was kept very simple to focus on what's going on with Kafka, without distractions.
     *
     * @param payOrder the request with Payment data.
     */
    @PostMapping(value = "/pay-order")
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentAttempt payOrder(@RequestBody PayOrder payOrder) {
        long paymentId = random.nextLong();
        long userId = random.nextLong();
        PaymentAttempt paymentAttempt = new PaymentAttempt(Math.abs(paymentId), Math.abs(userId), payOrder.getOrderId(),
                BigDecimal.valueOf(999.99), true);
        //send Payment Event to Kafka
        this.producer.sendPaymentEvent(paymentAttempt);
        return paymentAttempt;
    }

}
