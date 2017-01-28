<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/main.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/script/jquery.js"></script>



<!-- The script below is used to validate the password, its using JQuery -->

<script type="text/javascript">
	function onLoad() {

		$("#password").keyup(checkPasswordMatch);
		$("#confirmpassword").keyup(checkPasswordMatch);
		$("#details").submit(canSubmit);
	}

	function canSubmit() {

		var password = $("#password").val();
		var confirmpassword = $("#confirmpassword").val();

		if (password != confirmpassword) {
			//alert("passwords do not match]!")
			return false;
		} else {
			return true;
		}
	}

	function checkPasswordMatch() {

		var password = $("#password").val();
		var confirmpassword = $("#confirmpassword").val();

		if (password.length > 3 || confirmpassword > 3) {

			if (password == confirmpassword) {

				$("#matchpassword").text(
						"<fmt:message key='MatchedPassword.user.password'/>")
				$("#matchpassword").addClass("valid")
				$("#matchpassword").removeClass("error")

			} else {
				$("#matchpassword").text(
						"<fmt:message key='UnmatchedPassword.user.password'/>")
				$("#matchpassword").addClass("error")
				$("#matchpassword").removeClass("valid")

			}
			//alert(password + ": " + confirmpassword)
		}

	}

	$(document).ready(onLoad);
</script>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create new account</title>
</head>

<body>

	<h2>Create a new account</h2>

	<sf:form id="details" method="post"
		action="${pageContext.request.contextPath}/createaccount"
		commandName="user">

		<table class="formtable">
			<tr>
				<td class="label">UserName:</td>
				<td><sf:input class="control" path="username" name="username"
						type="text" /><br />
					<div class="error">
						<sf:errors path="username"></sf:errors>
					</div></td>
			</tr>
			<tr>
				<td class="label">Email:</td>
				<td><sf:input class="control" path="email" name="email"
						type="text" /><br />
					<div class="error">
						<sf:errors path="email"></sf:errors>
					</div></td>
			</tr>
			<tr>
				<td class="label">Password:</td>
				<td><sf:input class="control" path="password" name="password"
						type="password" id="password" /><br />
					<div class="error">
						<sf:errors path="password"></sf:errors>
					</div></td>
			</tr>
			<tr>
				<td class="label">Confirm Password:</td>
				<td><sf:input class="control" name="confirmpassword"
						path="confirmpassword" type="password" id="confirmpassword" />
					<div id="matchpassword"></div></td>
			</tr>
			<tr>
				<td></td>
				<td><input class="control" value="Create account" type="submit" /></td>
			</tr>

		</table>
	</sf:form>
</body>
</html>