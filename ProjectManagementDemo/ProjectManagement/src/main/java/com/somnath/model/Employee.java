package com.somnath.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
//@Proxy(lazy=false) // http://javarevisited.blogspot.in/2014/04/orghibernatelazyinitializationException-Could-not-initialize-proxy-no-session-hibernate-java.html
@Table(name="EMPLOYEE")
public class Employee {
	
	/**
	 * @Transient - If we want to ignore a column.
	 * 
	 * If we have a column of type java.util.Date, Hibernate will create the column of type DATETIME
	 * which stores the date along with time stamp. If the requirement is to store only Date or Time,
	 * we can use @Temporal(TemporalType.DATE) annotation.
	 */
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@Size(min=4, max=10)
	@Column(name="EMPLOYEE_ID")
	private String employeeId;
	
	@NotNull
	@NotEmpty
	@Size(min=4, max=15)
	@Column(name="NAME")
	private String name;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name="JOINING_DATE")
	private Date joiningDate;
	
	@Digits(integer=8, fraction=3)
	@Column(name="SALARY")
	private BigDecimal salary;
	
	private List<Project> projects;
	
	private List<Task> tasks;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	
	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	@Override
	public boolean equals(Object obj){
		if(!(obj instanceof Employee))
			return false;
		Employee emp=(Employee) obj;
		if(this.id==emp.getId())
			return true;
		return false;
	}
	
	@Override
	public String toString() {
	     return "Employee: [id=" + id + ", EmployeeId=" + employeeId + ", name=" 
	    		 	+ name + ", joiningDate=" + joiningDate + ", salary=" + salary  + "]";
	    }
}
