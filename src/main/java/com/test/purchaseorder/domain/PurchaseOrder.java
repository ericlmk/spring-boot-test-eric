package com.test.purchaseorder.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class PurchaseOrder {

	@Id
	private Integer purchaseOrderId;
	private String status;
	private Timestamp poCreateDate;
	private Timestamp poUpdateDate;
	
	
	// Reference to the client - please complete
	
	
	// Reference to line items - please complete
}
