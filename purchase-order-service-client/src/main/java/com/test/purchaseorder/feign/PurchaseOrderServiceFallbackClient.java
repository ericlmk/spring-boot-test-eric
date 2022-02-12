package com.test.purchaseorder.feign;

import com.test.purchaseorder.model.PurchaseOrderReq;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PurchaseOrderServiceFallbackClient implements PurchaseOrderServiceClient {

  @Override
  public ResponseEntity<?> getOrders(Date date) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<?> getOrder(int id) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<?> createOrder(PurchaseOrderReq requestBody) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<?> updateOrder(int id, PurchaseOrderReq requestBody) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<?> deleteOrder(int id) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

}