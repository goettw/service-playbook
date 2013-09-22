package serviceplaybook.model;

public class MongoLocalEntity {
private String collectionName;
private String id;
private String folder; 
public String getFolder() {
	return folder;
}
public MongoLocalEntity(String collectionName, String id, String folder) {
	super();
	this.collectionName = collectionName;
	this.id = id;
	this.folder = folder;
}
public void setFolder(String folder) {
	this.folder = folder;
}
public String getCollectionName() {
	return collectionName;
}
public void setCollectionName(String collectionName) {
	this.collectionName = collectionName;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
}