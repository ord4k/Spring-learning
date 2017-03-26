<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<table class="offers">
	<tr>
		<td>Name</td>
		<td>Email</td>
		<td>Offer</td>
	</tr>
	<c:forEach var="offer" items="${offers}">
		<tr>

			<td><c:out value="${offer.user.name}"></c:out></td>

			<td><a href="<c:url value='/message?uid=${offer.username}'></c:url>">contact</a></td>

			<td><c:out value="${offer.text}"></c:out></td>

		</tr>
	</c:forEach>
</table>
<p />

<c:choose>
	<c:when test="${hasOffer}">
		<a href="${pageContext.request.contextPath}/createoffer">Edit or
			delete your current offer</a>
	</c:when>
	<c:otherwise>
		<p>
			<a href="${pageContext.request.contextPath}/offers">Show
				currentoffers</a>
		</p>
	</c:otherwise>
</c:choose>
<p>
	<a href="${pageContext.request.contextPath}/createoffer">Create a new offer</a>
</p>




<p>
	<a href="${pageContext.request.contextPath}/newaccount">Create
		account</a>
</p>

<sec:authorize access="hasAuthority('ROLE_ADMIN')">
	<p>
		<a href="${pageContext.request.contextPath}/admin">Admin panel</a>
	</p>
</sec:authorize>



