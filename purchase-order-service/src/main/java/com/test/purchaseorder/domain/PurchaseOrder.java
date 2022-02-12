package com.test.purchaseorder.domain;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@XmlRootElement		// TO REMOVE
@Data
@Entity
@Table(name = "purchaseOrder")
public class PurchaseOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer purchaseOrderId;

	private String status;

	@CreationTimestamp
	@Column(updatable = false)
	private Timestamp poCreateDate;

	@UpdateTimestamp
	private Timestamp poUpdateDate;
	
	// Reference to the client
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "clientId")
	private Client client;
	
	// Reference to line items
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinTable(
		name = "purchaseOrderProductItemMapping",
		joinColumns = { @JoinColumn(name = "purchaseOrderId") },
		inverseJoinColumns = { @JoinColumn(name = "piId") }
	)
	private List<ProductItem> productItemList;


}
