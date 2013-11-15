package serviceplaybook.model.type;


public class Contact extends Link {
	
	private String role,username;
	
	public String getUsername() {
	    return username;
	}

	public void setUsername(String username) {
	    this.username = username;
	}

	boolean responsible;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isResponsible() {
		return responsible;
	}

	public void setResponsible(boolean responsible) {
		this.responsible = responsible;
	}
}
