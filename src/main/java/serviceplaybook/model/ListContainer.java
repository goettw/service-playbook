package serviceplaybook.model;

import java.util.List;

public class ListContainer <E>{
	private MongoLocalEntity localEntity;
	public MongoLocalEntity getLocalEntity() {
		return localEntity;
	}
	public void setLocalEntity(MongoLocalEntity localEntity) {
		this.localEntity = localEntity;
	}
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
	private String summary;
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
}
