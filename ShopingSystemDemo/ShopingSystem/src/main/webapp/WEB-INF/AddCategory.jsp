<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<html>
 
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Add Product</title>
 
<style>
 
    .error {
        color: #ff0000;
    }
</style>
 
</head>
 
<body>
 
    <h2>Add Category</h2>
  
    <form:form method="POST" commandName="category2">
        <form:input type="hidden" path="categoryId" id="categoryId"/>
        <table>
        	<tr>
                <td><label for="name">Category Name: </label> </td>
                <td><form:input path="categoryName" id="categoryName"/></td>
                <td><form:errors path="categoryName" cssClass="error"/></td>
            </tr>
            <tr>
                <td colspan="3">
                    <c:choose>
                        <c:when test="${edit}">
                            <input type="submit" value="Update"/>
                        </c:when>
                        <c:otherwise>
                            <input type="submit" value="Add"/>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
         </table>
    </form:form>
    <br/>
    <br/>
    Go back to <a href="<c:url value='/product/list' />">List of All Employees</a>
</body>
</html>