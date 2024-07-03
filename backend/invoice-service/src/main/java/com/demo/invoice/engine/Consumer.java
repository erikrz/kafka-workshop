package com.demo.invoice.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.demo.invoice.mapper.InvoiceMapper;
import com.demo.invoice.repository.InvoiceRepository;
import com.demo.models.PaymentAttempt;

import static java.lang.Boolean.TRUE;

/**
 * Kafka Consumer Class.
 */
@Service
public class Consumer {

    private final Logger logger = LoggerFactory.getLogger(Consumer.class);

    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;

    public Consumer(InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
    }

    /**
     * Listens to PaymentAttempts published by Kafka.
     *
     * @param payment Payment used as base for the Invoice.
     */
    @KafkaListener(topics = "payments", groupId = "invoice_group_id")
    public void consume(PaymentAttempt payment) {
        logger.info("#### -> Consumed message -> {}", payment);
        if (payment.getSuccess() == TRUE) {
            var invoice = invoiceMapper.toInvoice(payment);
            invoiceRepository.save(invoice);
        }else{
            logger.warn("#### -> Skipped invoice creation from payment -> {}", payment);
        }
    }

}
