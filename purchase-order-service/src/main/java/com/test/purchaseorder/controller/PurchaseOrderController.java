package com.test.purchaseorder.controller;

import com.test.purchaseorder.api.PurchaseOrderApi;
import com.test.purchaseorder.domain.Client;
import com.test.purchaseorder.domain.ProductItem;
import com.test.purchaseorder.domain.PurchaseOrder;
import com.test.purchaseorder.model.AppException;
import com.test.purchaseorder.model.AppExceptionRes;
import com.test.purchaseorder.model.PurchaseOrderReq;
import com.test.purchaseorder.service.ClientService;
import com.test.purchaseorder.service.ProductItemService;
import com.test.purchaseorder.service.PurchaseOrderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class PurchaseOrderController implements PurchaseOrderApi {

  private static final Logger logger = LogManager.getLogger(PurchaseOrderController.class);

  @Autowired
  private PurchaseOrderService purchaseOrderService;
  @Autowired
  private ClientService clientService;
  @Autowired
  private ProductItemService productItemService;

  public ResponseEntity<?> getOrders(@RequestParam(name = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
    try {
      List<PurchaseOrder> purchaseOrderList = this.purchaseOrderService.getOrderList(date, null);
      return new ResponseEntity<>(purchaseOrderList, HttpStatus.OK);
    } catch (AppException ex) {
      return new ResponseEntity<>(new AppExceptionRes(ex), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public ResponseEntity<?> getOrder(@PathVariable int id) {
    try {
      PurchaseOrder po = this.purchaseOrderService.getOrderById(id);
      if (po!=null) {
        return new ResponseEntity<>(po, HttpStatus.OK);
      } else {
        // 404 Not Found
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (AppException ex) {
      return new ResponseEntity<>(new AppExceptionRes(ex), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping(path = "/orders", consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> createOrder(@RequestBody PurchaseOrderReq requestBody) {
    try {
      PurchaseOrder po = new PurchaseOrder();
      po.setStatus(requestBody.getStatus());

      // Product Item
      po.setProductItemList(this.convertToSave(requestBody.getProductItemIdList()));

      // Set Client
      Client client = this.clientService.getClient(this.clientService.getUserName());
      po.setClient(client);

      // Create Order
      PurchaseOrder result = this.purchaseOrderService.createOrder(po);
      return new ResponseEntity<>(result, HttpStatus.CREATED);
    } catch (AppException ex) {
      return new ResponseEntity<>(new AppExceptionRes(ex), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping(path = "/orders/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> updateOrder(@PathVariable int id, @RequestBody PurchaseOrderReq requestBody) {
    try {
      PurchaseOrder po = this.purchaseOrderService.getOrderById(id);
      if (po==null) {
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
      if (!this.clientService.isRoleAdmin() && !this.clientService.getUserName().equals(po.getClient().getClientName())) {
        // No permission
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
      }
      // Update
      po.setStatus(requestBody.getStatus());
      po.setProductItemList(this.convertToSave(requestBody.getProductItemIdList()));
      PurchaseOrder result = this.purchaseOrderService.updateOrder(po);
      return new ResponseEntity<>(result, HttpStatus.OK);
    } catch (AppException ex) {
      return new ResponseEntity<>(new AppExceptionRes(ex), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public ResponseEntity<?> deleteOrder(@PathVariable int id) {
    try {
      boolean result = this.purchaseOrderService.deleteOrder(id);
      if (result) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      } else {
        // 404
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
    } catch (AppException ex) {
      return new ResponseEntity<>(new AppExceptionRes(ex), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  private List<ProductItem> convertToSave(List<Integer> piIdList) throws AppException {
    List<ProductItem> piList = new ArrayList<>();
    for (Integer piId: piIdList) {
      piList.add(this.productItemService.getProductItemById(piId));
    }
    return piList;
  }

}
