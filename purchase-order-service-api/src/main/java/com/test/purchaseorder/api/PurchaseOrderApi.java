package com.test.purchaseorder.api;

import com.test.purchaseorder.model.PurchaseOrderReq;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

public interface PurchaseOrderApi {

  @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
  @GetMapping("/orders")
  ResponseEntity<?> getOrders(@RequestParam(name = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date);

  @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
  @GetMapping("/orders/{id}")
  ResponseEntity<?> getOrder(@PathVariable int id);

  @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
  @PostMapping(path = "/orders", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<?> createOrder(@RequestBody PurchaseOrderReq requestBody);

  @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
  @PutMapping(path = "/orders/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<?> updateOrder(@PathVariable int id, @RequestBody PurchaseOrderReq requestBody);

  @PreAuthorize("hasAuthority('ADMIN')")
  @DeleteMapping(path = "/orders/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<?> deleteOrder(@PathVariable int id);

}
