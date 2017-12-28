package com.hit.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CATEGORY")
public class Category {

	@Id
	@GeneratedValue
	@Column(name="CATEGORY_ID", nullable=false)
	private int categoryId;
	
	@Column(name="NAME", nullable=false)
	private String categoryName;
	
	/*
	 * if we don't use mappedBy, then hibernate will create a third
	 * table to map one-to-many relation. 
	 * 
	 * CascadeType.ALL will delete all products associated with 
	 * the category which is being deleted.
	 */
	@OneToMany(cascade=CascadeType.ALL, mappedBy="category")
	//@JoinColumn(name = "PRODUCT_ID")
	List<Product> products;

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/*public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}*/
	
	
}
