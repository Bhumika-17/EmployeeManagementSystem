<%@page import="MyServlets.Employee"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Employee Details</title>
<link rel="stylesheet" type="text/css" href="styles.css">

</head>
<body>
<% Employee E = (Employee) request.getAttribute("data"); 
session.setAttribute("ename", E.name);
%>
<form method="POST" action="UpdateServlet">
Name : <%=E.name %> <br>
Contact : <input type="tel" name="econtact" value="<%=E.contact %>" required><br>
City : <input type="text" name="ecity" value="<%=E.city %>" required><br>
Salary : <input type="number" name="esalary" value="<%=E.salary %>" required><br>
<input type="submit" value="Update">
</form>
</body>
</html>