package com.test.purchaseorder.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.purchaseorder.domain.PurchaseOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {

  @Query(value = "from PurchaseOrder po where CAST(po.poCreateDate AS date) = :createDate and po.status <> 'Deleted'")
  public List<PurchaseOrder> getAllByCreateDate(@Param("createDate") Date createDate);

  public List<PurchaseOrder> findAllByStatusNot(String status);

  public PurchaseOrder findFirstByPurchaseOrderIdAndStatusNot(int purchaseOrderId, String status);

  @Query(value = "from PurchaseOrder po where po.status <> 'Deleted' and po.client.clientName = :clientName")
  public List<PurchaseOrder> getAllByClientName(@Param("clientName") String clientName);

}
