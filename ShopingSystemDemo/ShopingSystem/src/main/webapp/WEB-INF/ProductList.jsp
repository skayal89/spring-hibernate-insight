<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page isELIgnored ="false" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Product List</title>
 
    <style>
        tr:first-child{
            font-weight: bold;
            background-color: #C6C9C4;
        }
    </style>
 
</head>
 
 
<body>
    <h2>List of Products</h2>  
    <table>
        <tr>
            <td>Product Id</td><td>NAME</td><td>Price</td><td>Category</td><td>Description</td><td>Options</td>
        </tr>
        <c:forEach items="${productList}" var="product">
            <tr>
            <td>${product.productId}</td>
            <td>${product.productName}</td>
            <td>${product.price}</td>
            <td>${product.category.categoryName}</td>
            <td>${product.description}</td>
            <td><a href="<c:url value='/product/edit/${product.productId}' />">Edit</a> &nbsp;
            <a href="<c:url value='/product/delete/${product.productId}' />">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <a href="<c:url value='/product/new' />">Add New Product</a>
    <br/>
    <h2>List of Categories</h2>
    <table>
        <tr>
            <td>Category Id</td><td>NAME</td><td>Options</td>
        </tr>
        <c:forEach items="${categoryList}" var="category">
            <tr>
            <td>${category.categoryId}</td>
            <td>${category.categoryName}</td>
            <td><a href="<c:url value='/product/cat/edit/${category.categoryId}' />">Edit</a> &nbsp;
            <a href="<c:url value='/product/cat/delete/${category.categoryId}' />">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
    <br/>
    <a href="<c:url value='/product/cat/new' />">Add New Category</a>
    
</body>
</html>