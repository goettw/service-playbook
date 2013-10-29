package serviceplaybook.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class Profile implements UserDetails, Serializable {

    private Collection<String> authorityValues;

    public Collection<String> getAuthorityValues() {
	return authorityValues;
    }

    public void setAuthorityValues(Collection<String> authorityValues) {
	this.authorityValues = authorityValues;
    }

    /**
	 * 
	 */
    private static final long serialVersionUID = 2281662279365167094L;

    public Collection<GrantedAuthorityContainer> getAuthorities() {
	Collection<GrantedAuthorityContainer> authorities = new ArrayList<GrantedAuthorityContainer>();
	if (authorityValues == null || authorityValues.isEmpty())
	    return authorities;
	for (Iterator<String> it = authorityValues.iterator(); it.hasNext();) {
	    String auth = "ROLE_" + it.next();
	    System.out.println("auth - " + auth);
	    authorities.add(new GrantedAuthorityContainer(auth));
	}
	return authorities;
    }

    private String password;
    @Id
    @NotEmpty
    private String username;

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getAboutMe() {
	return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
	this.aboutMe = aboutMe;
    }

    public String getEmcFunction() {
	return emcFunction;
    }

    public void setEmcFunction(String emcFunction) {
	this.emcFunction = emcFunction;
    }

    public String getEmcProfileUrl() {
	return emcProfileUrl;
    }

    public void setEmcProfileUrl(String emcProfileUrl) {
	this.emcProfileUrl = emcProfileUrl;
    }

    String title;
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @NotEmpty
    private String emailAddress;

    private String aboutMe;
    private String emcFunction;
    private String emcProfileUrl;

    public String getFirstName() {
	return firstName;
    }

    public String getDisplayName() {
	String displayName = "";
	if (title != null && !title.equals("")) {
	    displayName = title + " ";
	}
	if (firstName != null && !firstName.equals("")) {
	    displayName += firstName + " ";
	}
	if (lastName != null && !lastName.equals("")) {
	    displayName += lastName;
	}
	return displayName;
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

    public String getEmailAddress() {
	return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
	this.emailAddress = emailAddress;
    }

    private boolean accountNonExpired, accountNonLocked, enabled, credentialsNonExpired;

    public boolean isCredentialsNonExpired() {
	return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
	this.credentialsNonExpired = credentialsNonExpired;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {

	this.password = password;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public boolean isAccountNonExpired() {
	return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
	this.accountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
	return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
	this.accountNonLocked = accountNonLocked;
    }

    public boolean isEnabled() {
	return enabled;
    }

    public void setEnabled(boolean enabled) {
	this.enabled = enabled;
    }

}
