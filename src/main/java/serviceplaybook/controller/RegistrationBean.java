package serviceplaybook.controller;

import org.hibernate.validator.constraints.NotEmpty;

public class RegistrationBean {
    @NotEmpty
    private String username;
    @NotEmpty
    private String emailAddress;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

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
