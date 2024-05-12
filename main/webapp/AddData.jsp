<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Employee Data</title>
<link rel="stylesheet" type="text/css" href="styles.css">

</head>
<body>
<center>
<h1> Welcome <%=session.getAttribute("username")%> ! </h1> 
<form method="POST" action="AddDataServlet">
Enter Employee's Name : <input type="text" name="ename" required><br><br>
Enter Employee's Contact : <input type="tel" name="econtact" required><br><br>
Select Employee's City: <br> <input type="radio" name="City" value="ahmedabad">Ahmedabad<br>
<input type="radio" name="City" value="delhi">Delhi<br>
<input type="radio" name="City" value="bangalore">Bangalore<br><br>
Enter Employee's salary : <input type="number" name="esalary" required><br><br>
<input type="submit" value="Submit Details">
</form>
</center>
</body>
</html>