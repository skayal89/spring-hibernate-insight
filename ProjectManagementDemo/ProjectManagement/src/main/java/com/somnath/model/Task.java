package com.somnath.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class Task {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty
	@NotNull
	@Size(min=4,max=10)
	@Column(name="TASK_ID", unique=true, nullable=false)
	private String taskId;
	
	@NotEmpty
	@NotNull
	@Column(name="TASK_NAME")
	private String taskName;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name="START_DATE")
	private Date startDate;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name="END_DATE")
	private Date deadline;
	
	@Column(name="PRIORITY")
	private int priority;
	
	private Project project;
	private Employee employee;
	
}
