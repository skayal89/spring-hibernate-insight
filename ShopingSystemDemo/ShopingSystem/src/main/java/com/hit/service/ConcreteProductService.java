package com.hit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hit.dao.ProductDAO;
import com.hit.model.Category;
import com.hit.model.Product;

@Service("productService")
@Transactional
public class ConcreteProductService implements ProductService {
	
	@Autowired
	private ProductDAO dao;

	public void add(Product product) {
		dao.add(product);
		
	}

	public void delete(int id) {
		dao.delete(id);
		
	}

	public Product getProduct(int id) {
		// TODO Auto-generated method stub
		return dao.getProduct(id);
	}

	public List<Product> getAllProducts() {
		// TODO Auto-generated method stub
		return dao.getAllProducts();
	}

	public void update(Product product) {
		dao.update(product);
		
	}

	public void addCategory(Category category) {
		dao.addCategory(category);
		
	}

	public void deleteCategory(int id) {
		dao.deleteCategory(id);
		
	}

	public void updateCategory(Category category) {
		dao.updateCategory(category);
		
	}

	public List<Category> getAllCategories() {
		// TODO Auto-generated method stub
		return dao.getAllCategories();
	}

	public Category findCategory(Product product) {
		// TODO Auto-generated method stub
		return dao.findCategory(product);
	}

	public Category getCategory(int id) {
		// TODO Auto-generated method stub
		return dao.getCategory(id);
	}

}
