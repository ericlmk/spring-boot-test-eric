package com.test.purchaseorder.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Client {

	@Id
	private Integer clientId;
	private String clientName;
	private String clientAddress;  // you may assume that all address details are held in this string
	
}
