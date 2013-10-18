package serviceplaybook.controller;

import org.hibernate.validator.constraints.NotEmpty;

public class RegistrationBean {
	@NotEmpty
	private String username;
	@NotEmpty
	private String emailAddress;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
}
