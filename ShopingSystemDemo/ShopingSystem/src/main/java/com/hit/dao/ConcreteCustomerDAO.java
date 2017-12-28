package com.hit.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hit.model.Customer;

@Repository
public class ConcreteCustomerDAO implements CustomerDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void add(Customer customer) {
		getSession().persist(customer);
		
	}
	
	public void delete(String id) {
		Session session=getSession();
		Customer customer=(Customer) session.load(Customer.class, id);
		// employee object returned by load is a proxy object. see getEmployee comments.
		if(customer!=null){
			// any operation on employee object will force to fetch the actual object.
			session.delete(customer);
		}
	}

	public Customer getCustomer(String id) {
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
		
		return (Customer) getSession().get(Customer.class, id);
	}

	public List<Customer> getAllCustomers() {
		Criteria criteria=getSession().createCriteria(Customer.class);
		//criteria.addOrder(Order.asc("USER_ID"));
		List<Customer> customers=criteria.list();
		return customers;
	}

	public void update(Customer customer) {
		getSession().update(customer);
	}

}
