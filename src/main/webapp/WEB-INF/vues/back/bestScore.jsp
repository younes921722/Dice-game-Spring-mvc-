<%@page import="com.bo.User"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page language="java" contentType="text/html; charset=windows-1256"
	pageEncoding="windows-1256"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/style/bootstrap.min.css"
	rel="stylesheet">

<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1256">
<title>App Game</title>
</head>
<body>



	<h1>Meilleurs scores (Avec Java)</h1>
	<%
	//On récupère les utilisateurs de la requete
		List<User> users = (List<User>) request.getAttribute("users");
		out.print("<table class=\"table\"><tr><td>Nom</td><td>Score</td></tr>");

		//On affiche le best score
		for (User it : users) {
			out.print("<tr><td>" + it.getNom() + " " + it.getPrenom() + "</td><td>" + it.getBestScore() + "</td></tr>");

		}
		out.print("</table>");
	%>


</body>
</html>