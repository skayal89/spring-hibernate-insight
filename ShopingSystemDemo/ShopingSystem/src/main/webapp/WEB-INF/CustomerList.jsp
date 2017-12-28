<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page isELIgnored ="false" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Customer List</title>
 
    <style>
        tr:first-child{
            font-weight: bold;
            background-color: #C6C9C4;
        }
    </style>
 
</head>
 
 
<body>
    <h2>List of Customers</h2>  
    <table>
        <tr>
            <td>Customer Id</td><td>NAME</td><td>Password</td><td>Options</td>
        </tr>
        <c:forEach items="${customerList}" var="customer">
            <tr>
            <td>${customer.userId}</td>
            <td>${customer.name}</td>
            <td>${customer.password}</td>
            <td><a href="<c:url value='/customer/edit/${customer.userId}' />">Edit</a> &nbsp;
            <a href="<c:url value='/customer/delete/${customer.userId}' />">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <a href="<c:url value='/customer/new' />">Add New Customer</a>
</body>
</html>