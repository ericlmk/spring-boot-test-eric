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
	private String clientAddress1;  
	private String clientAddress2;
	private String clientAddressCity;
	private String clientAddressPostCode;
	private String clientCountry;
	
}
