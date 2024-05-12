<%@page import="org.eclipse.jdt.internal.compiler.ast.ForeachStatement"%>
<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="MyServlets.Employee"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Displaying Employee Data</title>
<link rel="stylesheet" type="text/css" href="styles.css">
</head>
<body>
<center>
<h1>Displaying Employee Data</h1>

<table border="1" cellspacing="2" cellpadding="10">
<tr><th>Name</th><th>Contact</th><th>City</th><th>Salary</th><th>Update</th><th>Delete</th></tr>
<tr>

<% ArrayList<Employee> array = (ArrayList<Employee>) request.getAttribute("data");
if (array != null) {
   for (Employee E : array) {
       out.print("<tr>");
       out.print("<td>" + E.name + "</td>");
       out.print("<td>" + E.contact + "</td>");
       out.print("<td>" + E.city + "</td>");
       out.print("<td>" + E.salary + "</td>");
       out.print("<td><a href=\"UpdateRetrieveServlet?name="+E.name+"\">Update</a></td>");
       out.print("<td><a href=\"DeleteServlet?name="+E.name+"\">Delete</a></td>");
       out.print("</tr>");
   }
}
%>

</tr>
</table>
</center>
</body>
</html>