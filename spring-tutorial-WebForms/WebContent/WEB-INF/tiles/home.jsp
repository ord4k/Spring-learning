<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<p><a href="${pageContext.request.contextPath}/offers">Show current offers</a></p>
<p><a href="${pageContext.request.contextPath}/createoffer">Create offer</a></p>
<p><a href="${pageContext.request.contextPath}/newaccount">Create account</a></p>

<sec:authorize access="hasAuthority('ROLE_ADMIN')">
<p><a href="${pageContext.request.contextPath}/admin">Admin panel</a></p>
</sec:authorize>


