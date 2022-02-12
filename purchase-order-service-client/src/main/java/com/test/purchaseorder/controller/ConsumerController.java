package com.test.purchaseorder.controller;

import com.test.purchaseorder.feign.PurchaseOrderServiceClient;
import com.test.purchaseorder.model.PurchaseOrderReq;
import feign.FeignException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;

@RestController
public class ConsumerController {

  private static final Logger logger = LogManager.getLogger(ConsumerController.class);

  @Resource
  private PurchaseOrderServiceClient purchaseOrderServiceClient;

  @GetMapping("/orders")
  ResponseEntity<?> getOrders(@RequestParam(name = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
    try {
      ResponseEntity res = this.purchaseOrderServiceClient.getOrders(date);
      if (HttpStatus.OK.equals(res.getStatusCode())) {
        return new ResponseEntity<>(res.getBody(), res.getStatusCode());
      } else {
        return new ResponseEntity<>(res.getStatusCode());
      }
    } catch (FeignException ex) {
      // 404 / 500 or any error
      logger.log(Level.ERROR, "FeignException: " + ex.getMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/orders/{id}")
  ResponseEntity<?> getOrders(@PathVariable int id) {
    try {
      ResponseEntity res = this.purchaseOrderServiceClient.getOrder(1);
      if (HttpStatus.OK.equals(res.getStatusCode())) {
        return new ResponseEntity<>(res.getBody(), res.getStatusCode());
      } else {
        return new ResponseEntity<>(res.getStatusCode());
      }
    } catch (FeignException ex) {
      // 404 / 500 or any error
      logger.log(Level.ERROR, "FeignException: " + ex.getMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping(path = "/orders", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<?> createOrderJson(@RequestBody PurchaseOrderReq requestBody) {
    try {
      logger.log(Level.ERROR, "TEST: ");
      ResponseEntity res = this.purchaseOrderServiceClient.createOrder(requestBody);
      if (HttpStatus.CREATED.equals(res.getStatusCode())) {
        return new ResponseEntity<>(res.getBody(), res.getStatusCode());
      } else {
        return new ResponseEntity<>(res.getStatusCode());
      }
    } catch (FeignException ex) {
      // 404 / 500 or any error
      logger.log(Level.ERROR, "FeignException: " + ex.getMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping(path = "/orders/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<?> updateOrderJson(@PathVariable int id, @RequestBody PurchaseOrderReq requestBody) {
    try {
      ResponseEntity res = this.purchaseOrderServiceClient.createOrder(requestBody);
      if (HttpStatus.OK.equals(res.getStatusCode())) {
        return new ResponseEntity<>(res.getBody(), res.getStatusCode());
      } else {
        return new ResponseEntity<>(res.getStatusCode());
      }
    } catch (FeignException ex) {
      // 404 / 500 or any error
      logger.log(Level.ERROR, "FeignException: " + ex.getMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping(path = "/orders/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<?> deleteOrderJson(@PathVariable int id) {
    try {
      ResponseEntity res = this.purchaseOrderServiceClient.deleteOrder(id);
      return new ResponseEntity<>(res.getStatusCode());
    } catch (FeignException ex) {
      // 404 / 500 or any error
      logger.log(Level.ERROR, "FeignException: " + ex.getMessage());
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}
