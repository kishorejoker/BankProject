<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display Details</title>
</head>
<body>
<div class="container">
  <a href="http://localhost:8080/BankProject/menu">menu</a>
				  	<h2>Customer Details</h2>
				  
				  <table class="table">
					    <thead>
						      <tr>
						        <th>Account Number</th>
						        <th>Customer Name</th>
						        <th>Mobile Number</th>
						        <th>Balance</th>
						        <th>Email</th>
						         <th>Address</th>
						      </tr>
					    </thead>
					    <tbody>
					    	<c:forEach var="c" items="${customerdetails}">
						      <tr class="success">
						        <td>${c.accountno}</td>				        
						        <td>${c.name}</td>
						        <td>${c.mobno}</td>
						        <td>${c.balance}</td>
						        <td>${c.emailId}</td>	
						        <td>${c.address}</td>					        
						      </tr>      
						    </c:forEach>
					    </tbody>
				  </table>
			</div>
</body>
</html>