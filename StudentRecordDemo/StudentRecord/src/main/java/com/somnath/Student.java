package com.somnath;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

public class Student {

	@NotEmpty
	@Size(min=4, max=10, message="name size must be between {min} and {max} characters")
	String name; 
	
	@NotEmpty
	@Size(min=3, max=10)
	String rollNumber; 
	
	String dept;
	
	@NotEmpty
	@Hobby(hobbies={"Music", "Movie", "Sport"}, message="valid hobbies are {hobbies}")
	String hobby;
	
	long contact;
	
	@Past
	@DateTimeFormat(pattern="dd/MM/yyyy")
	Date dateOfBirth;
	
	String gender;
	List<String> skills;
	Address address;
	boolean presentAddress;
	List<String> frameworks;
	
	@NotEmpty
	@Email
	String email;
	
	@NotNull @Min(18) @Max(40)
	Integer age;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public List<String> getFrameworks() {
		return frameworks;
	}

	public void setFrameworks(List<String> frameworks) {
		this.frameworks = frameworks;
	}

	public boolean getPresentAddress() {
		return presentAddress;
	}

	public void setPresentAddress(boolean presentAddress) {
		this.presentAddress = presentAddress;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getContact() {
		return contact;
	}

	public void setContact(long contact) {
		this.contact = contact;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public List<String> getSkills() {
		return skills;
	}

	public void setSkills(List<String> skills) {
		this.skills = skills;
	}

	/*@Autowired
	public Student(String name, String rollNumber) {
		this.name = name;
		this.rollNumber = rollNumber;
	}*/
	
	@Override
	public String toString()
	{
		return rollNumber+" "+name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(String rollNumber) {
		this.rollNumber = rollNumber;
	}
	

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	
}
