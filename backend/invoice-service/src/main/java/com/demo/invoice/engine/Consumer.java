package com.demo.invoice.engine;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.demo.invoice.entity.Invoice;
import com.demo.invoice.repository.InvoiceRepository;
import com.demo.models.PaymentAttempt;

/**
 * Kafka Consumer Class.
 */
@Service
public class Consumer {

    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    private final InvoiceRepository invoiceRepository;

    public Consumer(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    /**
     * Listens to PaymentAttempts published by Kafka.
     *
     * @param payment Payment used as base for the Invoice.
     */
    @KafkaListener(topics = "payments", groupId = "invoice_group_id")
    public void consume(PaymentAttempt payment) {
        logger.info("#### -> Consumed message -> {}", payment);
        if (payment.getSuccess()) {
            invoiceRepository.save(createInvoice(payment));
        }
    }

    /**
     * Invoice creation method.
     * @param payment Base payment from which the Invoice will be created.
     * @return an Invoice Instance.
     */
    private Invoice createInvoice(PaymentAttempt payment) {
        Invoice invoice = new Invoice();
        invoice.setUserId(payment.getUserId());
        invoice.setPaymentId(payment.getPaymentId());
        invoice.setOrderId(payment.getOrderId());
        BigDecimal subtotal = payment.getTotalAmount()
                .multiply(BigDecimal.valueOf(100L))
                .divide(BigDecimal.valueOf(116L), 2, RoundingMode.HALF_UP);
        invoice.setSubtotal(subtotal);
        invoice.setTax(payment.getTotalAmount().subtract(subtotal));
        return invoice;
    }
}
