package com.test.purchaseorder.domain;

import javax.persistence.*;

import com.test.purchaseorder.component.AttributeEncryptor;
import lombok.Data;

@Data
@Entity
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer clientId;

	@Convert(converter = AttributeEncryptor.class)
	private String clientName;

	@Convert(converter = AttributeEncryptor.class)
	private String clientAddress1;

	@Convert(converter = AttributeEncryptor.class)
	private String clientAddress2;

	@Convert(converter = AttributeEncryptor.class)
	private String clientAddressCity;

	@Convert(converter = AttributeEncryptor.class)
	private String clientAddressPostCode;

	@Convert(converter = AttributeEncryptor.class)
	private String clientCountry;
	
}
