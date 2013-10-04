package serviceplaybook.model.type;

import org.hibernate.validator.constraints.NotEmpty;

public class Link {
@NotEmpty
private String label;
@NotEmpty
private String url;
public String getLabel() {
	return label;
}
public void setLabel(String label) {
	this.label = label;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
}
