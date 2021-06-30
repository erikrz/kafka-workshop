/*
 * Copyright (c) 2021 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.demo.payment.dto;

import java.beans.ConstructorProperties;

/**
 * Immutable Pay Order Class.
 */
public class PayOrder {

    private final Long orderId;

    @ConstructorProperties("orderId")
    public PayOrder(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderId() {
        return orderId;
    }

}
