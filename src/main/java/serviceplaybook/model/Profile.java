package serviceplaybook.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.userdetails.UserDetails;

public class Profile implements UserDetails , Serializable{

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
		for (Iterator<String> it = authorityValues.iterator(); it.hasNext();) {
			authorities.add(new GrantedAuthorityContainer(it.next()));
		}
		return authorities;
	}

	private String password;
	@Id
	private String username;
	private boolean accountNonExpired, accountNonLocked, enabled,
			credentialsNonExpired;

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
