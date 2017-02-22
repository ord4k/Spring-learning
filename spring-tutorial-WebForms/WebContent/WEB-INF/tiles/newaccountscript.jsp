<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
	function onLoad() {

		$("#password").keyup(checkPasswordsMatch);
		$("#confirmpassword").keyup(checkPasswordsMatch);
		$("#details").submit(canSubmit);
	}

	function canSubmit() {

		var password = $("#password").val();
		var confirmpassword = $("#confirmpassword").val();

		if (password != confirmpassword) {
			alert("<fmt:message key='UnmatchedPasswords.user.password' />")
			return false;
		} else {
			return true;
		}
	}

	function checkPasswordsMatch() {

		var password = $("#password").val();
		var confirmpassword = $("#confirmpassword").val();

		if (password.length > 3 || confirmpassword > 3) {

			if (password == confirmpassword) {

				$("#matchpassword").text(
						"<fmt:message key='MatchedPasswords.user.password'/>")
				$("#matchpassword").addClass("valid")
				$("#matchpassword").removeClass("error")

			} else {
				$("#matchpassword").text("<fmt:message key='UnmatchedPasswords.user.password'/>")
				$("#matchpassword").addClass("error")
				$("#matchpassword").removeClass("valid")

			}
			//alert(password + ": " + confirmpassword)
		}

	}

	$(document).ready(onLoad);
</script>