<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- required to include jstl-1.2.jar [http://stackoverflow.com/questions/8400301/cout-unknown-tag/8400733#8400733] -->
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<title>Error</title>
</head>
<body>
<h2>Exception occurred</h2><br/>
<h3>Request URL: ${url}</h3>
<h3>Message: ${exception.message}</h3>
<h3>Stack Trace:</h3>
<c:forEach items="${exception.stackTrace}" var="ste" varStatus="tStatus">
	<br/>[${tStatus.index}]: ${ste} 
</c:forEach>
</body>
</html>