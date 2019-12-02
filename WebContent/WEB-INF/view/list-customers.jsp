<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>List Customers</title>

<link type="text/css"
	  rel="stylesheet"
	  href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
		<div id="wrapper">
		<div id = "header">
		<h2>CRM- Customer Relationship Manager</h2>
		</div>
		</div>
		
		<div id="container">
		<div id="content">
		
		<!-- Add a new button (Add Customer) -->
		
		<input type="button" value="Add Customer" 
		 onclick = "window.location.href='showFormForAdd'; return false;"
		  class ="add-button"/>
		  
		 <!-- Add the search box -->
		 
		 <form:form action="Search" method="get"> 
		 Search Customer:<input type="text" name="theSearchName"/>
		<input type="submit" value="Search" class="add-button"/>
		</form:form>
		
		<!-- Create table -->
		
		<table>
		<tr>
		<th>FirstName</th>
		<th>LastName</th>
		<th>Email</th>
		<th>Action</th>
		</tr>
		
		<!-- Loop over and print out customer -->
		
		<c:forEach var="tempcustomer" items="${customer}">
		
		<!-- Construct an "updateLink" with customer id -->
		
		<c:url var="updateLink" value="/customer/showFormForUpdate">
		<c:param name="customerId" value="${tempcustomer.id}"/>
		</c:url>
		
		<c:url var="deleteLink" value="/customer/delete">
		<c:param name="customerId" value="${tempcustomer.id}"/>
		</c:url>
		
		<tr>
		<td> ${tempcustomer.firstName}</td>
		<td> ${tempcustomer.lastName}</td>
		<td> ${tempcustomer.email}</td>
		<td><a href="${updateLink}">Update</a>
		| <a href="${deleteLink}" onclick="if(!(confirm('Are you sure you want to delete this customer..?'))) return false">Delete</a></td>
		</tr>
		</c:forEach>
		</table>
		
		</div>
		</div>
</body>
</html>