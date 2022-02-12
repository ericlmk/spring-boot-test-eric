package com.test.purchaseorder.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PurchaseOrderReq implements Serializable {

  private String status;
  private List<Integer> productItemIdList;

}
