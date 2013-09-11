package serviceplaybook.model;

import java.util.List;

public class ListContainer <E>{
	public String getLabel() {
		return Label;
	}
	public void setLabel(String label) {
		Label = label;
	}
	public List<E> getEntries() {
		return entries;
	}
	public void setEntries(List<E> entries) {
		this.entries = entries;
	}
	private String Label;
	private List<E> entries;
}
