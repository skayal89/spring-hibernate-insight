<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>
<body>
<form:errors path="student1.*"></form:errors>
<form:form action="/StudentRecord/doRegister" method="post" commandName="student2">
	<p>Name: <input name="name" type="text"/></p>
	<p>Roll: <input name="rollNumber" type="text"/></p>
	<p>Contact: <input name="contact" type="text"/></p>
	<p>Date: <input name="dateOfBirth" type="text"/></p>
	<p>Age: <input name="age" type="text"/></p>
	<p>Email: <input name="email" type="text"/></p>
	<p>Gender: 
		<form:radiobuttons path="gender" items="${genderList}"></form:radiobuttons>
	</p>
	<div>Address: 
		<p>City: <input name="address.city" type="text"/></p>
		<p>State: <input name="address.state" type="text"/></p>
		<p>Pin: <input name="address.pin" type="text"/></p>
	</div>
	<form:checkbox path="presentAddress" value="true"/> Present Address
	<p>Department
	<select name="dept">
		<option value="Computer Science">Computer Science</option>
		<option value="Electronics and Communication">Electronics and Communication</option>
		<option value="Electrical Engineering">Electrical Engineering</option>
	</select>
	</p>
	<p>Skills: 
	<select name="skills" multiple="multiple">
		<option value="Java">Java</option>
		<option value="C">C</option>
		<option value="C++">C++</option>
		<option value="Python">Python</option>
	</select>
	</p>
	<p> Framework Known:
		<form:checkboxes items="${frameworkList}" path="frameworks"/>
	</p>
	<p>Hobby: <input name="hobby" type="text"/></p>
	<input type="submit" value="Submit">
</form:form>

</body>
</html>