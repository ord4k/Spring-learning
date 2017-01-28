package com.caveofprogramming.spring.web.dao;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class User {

	@NotBlank()
	@Size(min = 4)
	private String username;

	@NotBlank()
	@Size(min = 4, max = 16)
	private String password;

	@Email
	@NotBlank()
	private String email;
	private boolean enabled = false;
	private String authority;

	private String confirmpassword;

	public User() {

	}

	public User(String username, String password, String confirmpassword, String email, boolean enabled,
			String authority) {

		this.username = username;
		this.password = password;
		this.confirmpassword = confirmpassword;
		this.enabled = enabled;
		this.email = email;
		this.authority = authority;

	}

	public String getConfirmpassword() {
		return confirmpassword;
	}

	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
