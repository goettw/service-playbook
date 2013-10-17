package serviceplaybook.model;

import org.springframework.security.core.GrantedAuthority;

public class GrantedAuthorityContainer implements GrantedAuthority {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1068577768276509604L;
	String authority;
public GrantedAuthorityContainer(String authority) {
	setAuthority(authority);
}
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
