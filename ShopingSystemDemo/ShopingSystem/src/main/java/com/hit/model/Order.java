package com.hit.model;

import java.util.*;

import javax.persistence.*;

@Entity  
@Table(name="ORDER")
public class Order {
	
	@Id
	@Column(name = "ORDER_ID")
	private int orderId;
	
	@Column(name = "ORDER_DATE")
	private Date orderedDate;
	
	@Column(name = "SHIPPED_DATE")
	private Date shippedDate;
	
	@Column(name = "SHIPPED_ADDRESS")
	private String shipAddress;
	
	@Column(name = "TOTAL")
	private int total;
	
	@Column(name = "OrderStatus")
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
	private List<OrderItem> orderItems;
}
