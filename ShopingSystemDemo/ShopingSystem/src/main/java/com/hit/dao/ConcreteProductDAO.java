package com.hit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hit.model.Category;
import com.hit.model.Customer;
import com.hit.model.Product;

@Repository
public class ConcreteProductDAO implements ProductDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void add(Product product) {
		/*
		 * persist() will throw error if key already present.
		 */
		//getSession().persist(product);
		getSession().saveOrUpdate(product);
	}

	public void delete(int id) {
		Session session=getSession();
		Product product=(Product) session.load(Product.class, id);
		// employee object returned by load is a proxy object. see getEmployee comments.
		if(product!=null){
			// any operation on employee object will force to fetch the actual object.
			session.delete(product);
		}
		
	}

	public Product getProduct(int id) {
		/**
		 * MUST READ:
		 * Exception thrown if we use load() instead of get() because of Lazy Initialization: 
		 * "org.hibernate.LazyInitializationException: could not initialize proxy - no Session"
		 * we can use @Proxy(lazy=false) on top of Entity class to disable Lazy initialization, 
		 * which is not recommended because of slow performance OR, we can use get(). What 
		 * actually happening here is, session has been closed, so proxy object cannot be
		 * reinitialize.
		 * 
		 * http://javarevisited.blogspot.in/2012/07/hibernate-get-and-load-difference-interview-question.html
		 * http://javarevisited.blogspot.in/2014/04/orghibernatelazyinitializationException-Could-not-initialize-proxy-no-session-hibernate-java.html
		 */
		
		
		/**
		 * return statement (1) will throw error because returned employee object is 
		 * lazily instantiate by load() method. When this method returns session will 
		 * be closed and whenever caller method try to access the employee object, 
		 * hibernate will throw an exception as it's a proxy object and session has 
		 * been closed. But in example (2), if we print (or apply any other operation 
		 * before returning) the employee object, then the proxy object will be 
		 * initialized and no exception will be thrown, which is same as get() method.
		 * 
		 * (1)  return (Employee) getSession().load(Employee.class, new Integer(id));
		 * 
		 * (2)  Session session=getSession();
		 *		Employee employee=(Employee) session.load(Employee.class, new Integer(id));
		 *		System.out.println("getEmployee:"+employee);
		 *		return employee;
		 * 
		 */
		
		return (Product) getSession().get(Product.class, id);
	}

	public List<Product> getAllProducts() {
		Criteria criteria=getSession().createCriteria(Product.class);
		//criteria.addOrder(Order.asc("USER_ID"));
		List<Product> products=criteria.list();
		return products;
	}

	public void update(Product product) {
		getSession().update(product);
		
	}

	public void addCategory(Category category) {
		getSession().saveOrUpdate(category);
		
	}

	public void deleteCategory(int id) {
		Session session=getSession();
		Category category=(Category) session.load(Category.class, id);
		// employee object returned by load is a proxy object. see getEmployee comments.
		if(category!=null){
			// any operation on employee object will force to fetch the actual object.
			session.delete(category);
		}
		
	}

	public void updateCategory(Category category) {
		getSession().update(category);
		
	}

	public List<Category> getAllCategories() {
		Criteria criteria=getSession().createCriteria(Category.class);
		//criteria.addOrder(Order.asc("USER_ID"));
		List<Category> categories=criteria.list();
		return categories;
	}

	public Category findCategory(Product product) {
		Query query = getSession().createQuery("SELECT c.NAME FROM PRODUCT P INNER JOIN P.category c");
		List<String> criteriaNames=query.list();
		System.out.println(criteriaNames);
		return null;
	}
	
	public Category getCategory(int id) {
		return (Category) getSession().get(Category.class, id);
	}

}
