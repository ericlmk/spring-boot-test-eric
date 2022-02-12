package com.test.purchaseorder.domain;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "productItem")
public class ProductItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer piId;
	private String piName;
	private Integer piQuantity;
	private Double piCost;
	private Double lineCost;

}
