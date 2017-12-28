package com.hit.dao;

import java.util.List;

import com.hit.model.Category;
import com.hit.model.Product;

public interface ProductDAO {
	void add(Product product);
	void delete(int id);
	Product getProduct(int id);
	List<Product>	getAllProducts();
	void update(Product product);
	
	void addCategory(Category category);
	void deleteCategory(int id);
	void updateCategory(Category category);
	List<Category> getAllCategories();
	Category findCategory(Product product);
	Category getCategory(int id);
}
