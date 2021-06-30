/*
 * Copyright (c) 2021 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.demo.models;

import java.beans.ConstructorProperties;
import java.math.BigDecimal;

/**
 * Immutable Payment POJO class.
 */
public class PaymentAttempt {

    private final Long paymentId;
    private final Long userId;
    private final Long orderId;
    private final BigDecimal totalAmount;
    private final Boolean success;

    @ConstructorProperties({"paymentId", "userId", "orderId", "totalAmount", "success"})
    public PaymentAttempt(Long paymentId, Long userId, Long orderId, BigDecimal totalAmount, Boolean success) {
        this.paymentId = paymentId;
        this.userId = userId;
        this.orderId = orderId;
        this.totalAmount = totalAmount;
        this.success = success;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public Boolean getSuccess() {
        return success;
    }

    @Override public String toString() {
        return "PaymentAttempt{" +
                "paymentId=" + paymentId +
                ", userId=" + userId +
                ", totalAmount=" + totalAmount +
                ", success=" + success +
                '}';
    }
}
