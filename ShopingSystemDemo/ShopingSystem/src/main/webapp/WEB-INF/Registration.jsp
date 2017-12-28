<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<html>
 
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Employee Registration Form</title>
 
<style>
 
    .error {
        color: #ff0000;
    }
</style>
 
</head>
 
<body>
 
    <h2>Registration Form</h2>
  
    <form:form method="POST" commandName="customer2">
        <form:input type="hidden" path="account.accountId" id="accountId"/>
        <table>
        	<tr>
                <td><label for="name">customerId: </label> </td>
                <td><form:input path="userId" id="userId"/></td>
                <td><form:errors path="userId" cssClass="error"/></td>
            </tr>
            <tr>
                <td><label for="name">Name: </label> </td>
                <td><form:input path="name" id="name"/></td>
                <td><form:errors path="name" cssClass="error"/></td>
            </tr>
         
            <tr>
                <td><label for="password">Password: </label> </td>
                <td><form:input path="password" id="password"/></td>
                <td><form:errors path="password" cssClass="error"/></td>
            </tr>
     
            <tr>
                <td><label for="email">Email: </label> </td>
                <td><form:input path="email" id="email"/></td>
                <td><form:errors path="email" cssClass="error"/></td>
            </tr>
            
            <tr>
                <td><label for="address">Address: </label> </td>
                <td><form:input path="address" id="address"/></td>
                <td><form:errors path="address" cssClass="error"/></td>
            </tr>
            
            <tr>
                <td><label for="address">Billing Address: </label> </td>
                <td><form:input path="account.billingAddress" id="billingAddress"/></td>
                <td><form:errors path="account.billingAddress" cssClass="error"/></td>
            </tr>
     
     
            <tr>
                <td colspan="3">
                    <c:choose>
                        <c:when test="${edit}">
                            <input type="submit" value="Update"/>
                        </c:when>
                        <c:otherwise>
                            <input type="submit" value="Register"/>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </table>
    </form:form>
    <br/>
    <br/>
    Go back to <a href="<c:url value='/customer/list' />">List of All Employees</a>
</body>
</html>