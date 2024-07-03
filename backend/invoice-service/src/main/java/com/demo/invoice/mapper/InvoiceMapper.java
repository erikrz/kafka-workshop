package com.demo.invoice.mapper;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Component;

import com.demo.invoice.entity.Invoice;
import com.demo.models.PaymentAttempt;

@Component
public class InvoiceMapper {

    /**
     * Invoice creation method.
     * @param payment Base payment from which the Invoice will be created.
     * @return an Invoice Instance.
     */
    public Invoice toInvoice(PaymentAttempt payment) {
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
