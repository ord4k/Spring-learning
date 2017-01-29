<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>

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
