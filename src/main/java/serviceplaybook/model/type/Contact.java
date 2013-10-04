package serviceplaybook.model.type;

import org.hibernate.validator.constraints.NotEmpty;

public class Contact extends Link {
	@NotEmpty
	String role;
	@NotEmpty
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
