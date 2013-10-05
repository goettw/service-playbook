package serviceplaybook.model;

import java.io.Serializable;

public class ServiceListEntry implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1256007955855394059L;
	private String id, label;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
