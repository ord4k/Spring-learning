<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<a class="title" href="<c:url  value='/'/>">Offers</a>

<sec:authorize access="!isAuthenticated()">
<p><a class= "login" href="${pageContext.request.contextPath}/login">Login</a></p>
</sec:authorize>



<!-- Loggout formula for Spring 4.x with csrf.token +including view control based on the current user's role -->
<sec:authorize access="isAuthenticated()">
<c:url var="logoutUrl" value="/logout"/>
<form class="logout" action="${logoutUrl}" method="post">
<input type="submit" value="Log out"/> <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</sec:authorize>


<!-- End of loggout -->