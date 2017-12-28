package com.hit.model;

import javax.persistence.*;

@Entity
@Table(name="PRODUCT")
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PRODUCT_ID", nullable=false)
	private int productId;
	
	@Column(name="PRODUCT_NAME", nullable=false)
	private String productName;
	
	@Column(name="PRICE", nullable=false)
	private long price;
	
	/*
	 * if cascade is not defined then following error will be encountered:
	 * "TransientObjectException: object references an unsaved transient 
	 * instance - save the transient instance before flushing"
	 * http://stackoverflow.com/questions/2302802/object-references-an-unsaved-transient-instance-save-the-transient-instance-be/2302814
	 * 
	 * if we use CascadeType.ALL or CascadeType.PERSIST then for every insertion of a product,
	 * a new category will be inserted to category table; 
	 */
	@ManyToOne(cascade={CascadeType.REFRESH, CascadeType.MERGE})
	@JoinColumn(name = "CATEGORY_ID")
	private Category category;
	
	@Column(name="DESCRIPTION")
	private String description;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
