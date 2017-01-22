<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/static/css/main.css">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p><a href="${pageContext.request.contextPath}/offers">Show current offers</a></p>
<p><a href="${pageContext.request.contextPath}/createoffer">Create offer</a></p>
<p><a href="${pageContext.request.contextPath}/newaccount">Create account</a></p>
<sec:authorize access="!isAuthenticated()">
<p><a href="${pageContext.request.contextPath}/login">Login</a></p>
</sec:authorize>
<sec:authorize access="hasAuthority('admin')">
<p><a href="${pageContext.request.contextPath}/admin">Admin panel</a></p>
</sec:authorize>

<!-- Loggout formula for Spring 4.x with csrf.token +including view control based on the current user's role -->
<sec:authorize access="isAuthenticated()">
<c:url var="logoutUrl" value="/logout"/>
<form action="${logoutUrl}" method="post">
<input type="submit" value="Log out"/> <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

</sec:authorize>

<!-- End of loggout -->





</body>
</html>