<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
<p>
${student1.name} 
<br/> ${student1.rollNumber} 
<br/> ${student1.contact} 
<br/> ${student1.dateOfBirth} 
<br/> ${student1.gender}
<br/> ${student1.age} 
<br/> ${student1.email}
<br/> ${student1.address}
<br/> Present Address: ${student1.presentAddress}
<br/>${student1.dept} 
<br/> ${student1.skills}
<br/> ${student1.frameworks}
</p>
</body>
</html>