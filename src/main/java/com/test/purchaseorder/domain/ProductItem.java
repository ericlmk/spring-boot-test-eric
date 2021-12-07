package com.test.purchaseorder.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class ProductItem {
	
	@Id
	private Integer piId;
	private String piName;
	private Integer piQuantity;
	private Double piCost;
	private Double lineCost;

}
