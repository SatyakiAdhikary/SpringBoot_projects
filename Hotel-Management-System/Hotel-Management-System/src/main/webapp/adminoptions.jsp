<%@page import="com.jsp.hotel_mangement_system.entities.Admin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
	Admin a = (Admin) session.getAttribute("admininfo");
	if (a != null) {
	%>

	${message}
	<br>
	<a href="fetchunapprovedhotels">Approve Hotel</a>
	<br>
	<a href="fetchunblocedhotels">Block Hotel</a>
	<br>
	<a href="fetchblockhotels">Unblock Hotel</a>
	<br>
	<a href="providediscount">Provide Discount</a>
	<%
	} else {
	%>
	<a href="Adminlogin.jsp">Please Login First</a>
<%
}
%>
</body>
</html>