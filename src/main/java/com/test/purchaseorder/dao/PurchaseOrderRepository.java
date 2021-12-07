package com.test.purchaseorder.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.purchaseorder.domain.PurchaseOrder;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {

}
