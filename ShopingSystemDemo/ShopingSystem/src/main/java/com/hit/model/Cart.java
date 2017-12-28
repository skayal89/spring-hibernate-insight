package com.hit.model;

import java.util.List;

import javax.persistence.*;

@Entity  
@Table(name="CART")
public class Cart {
	
	@Id
	@Column(name = "CART_ID")
	private int cartId;
	private List<OrderItem> orderItems;
}
