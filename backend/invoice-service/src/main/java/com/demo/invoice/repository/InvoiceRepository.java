/*
 * Copyright (c) 2021 Nextiva, Inc. to Present.
 * All rights reserved.
 */

package com.demo.invoice.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.demo.invoice.entity.Invoice;

/**
 * Invoice Repository.
 */
@RepositoryRestResource(collectionResourceRel = "invoices", path = "invoices")
public interface InvoiceRepository extends PagingAndSortingRepository<Invoice, Long> {

    /**
     * List of invoices with a given orderId.
     * @param orderId the orderId.
     * @return list of invoices.
     */
    List<Invoice> findByOrderId(@Param("orderId") Long orderId);
}