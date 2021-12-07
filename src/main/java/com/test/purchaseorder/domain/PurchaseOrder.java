package com.test.purchaseorder.domain;

import java.sql.Timestamp;

import javax.persistence.Id;

import lombok.Data;

@Data
public class PurchaseOrder {

	@Id
	private Integer purchaseOrderId;
	private String status;
	private Timestamp poCreateDate;
	private Timestamp poUpdateDate;
	
	// other meta information
}
