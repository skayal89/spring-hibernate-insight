package com.somnath.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.somnath.model.Employee;

@Repository
public class ConcreteEmplyeeDAO implements EmplyeeDAO {
	
	@Autowired
	SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public void add(Employee employee) {
		getSession().persist(employee);
		
	}

	public void delete(int id) {
		Session session=getSession();
		Employee employee=(Employee) session.load(Employee.class, new Integer(id));
		// employee object returned by load is a proxy object. see getEmployee comments.
		if(employee!=null){
			// any operation on employee object will force to fetch the actual object.
			session.delete(employee);
		}
	}

	public Employee getEmployee(int id) {
		/**
		 * MUST READ:
		 * Exception thrown if we use load() instead of get() because of Lazy Initialization: 
		 * "org.hibernate.LazyInitializationException: could not initialize proxy - no Session"
		 * we can use @Proxy(lazy=false) on top of Entity class to disable Lazy initialization, 
		 * which is not recommended because of slow performance OR, we can use get(). What 
		 * actually happening here is, session has been closed, so proxy object cannot be reinitialize.
		 * 
		 * http://javarevisited.blogspot.in/2012/07/hibernate-get-and-load-difference-interview-question.html
		 * http://javarevisited.blogspot.in/2014/04/orghibernatelazyinitializationException-Could-not-initialize-proxy-no-session-hibernate-java.html
		 */
		
		
		/**
		 * return statement (1) will throw error because returned employee object is lazily instantiate 
		 * by load() method. When this method returns session will be closed and whenever caller method try
		 * to access the employee object, hibernate will throw an exception as it's a proxy object and session
		 * has been closed. But in example (2), if we print (or apply any other operation before returning) 
		 * the employee object, then the proxy object will be initialized and no exception will be 
		 * thrown, which is same as get() method.
		 * 
		 * (1)  return (Employee) getSession().load(Employee.class, new Integer(id));
		 * 
		 * (2)  Session session=getSession();
		 *		Employee employee=(Employee) session.load(Employee.class, new Integer(id));
		 *		System.out.println("getEmployee:"+employee);
		 *		return employee;
		 * 
		 */
		
		return (Employee) getSession().get(Employee.class, new Integer(id));
	}

	public List<Employee> getAllEmployees() {
		Criteria criteria=getSession().createCriteria(Employee.class);
		criteria.addOrder(Order.asc("employeeId"));
		List<Employee> employees=criteria.list();
		return employees;
	}

	public void update(Employee employee) {
		getSession().update(employee);
	}
	
}
