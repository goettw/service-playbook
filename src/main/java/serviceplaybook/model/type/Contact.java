package serviceplaybook.model.type;


public class Contact extends Link {
 String role;
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
