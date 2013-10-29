package serviceplaybook.model;

public class Comment extends ActionLogItem {
    private String comment;
    private MongoLocalEntity localEntity;

    public MongoLocalEntity getLocalEntity() {
	return localEntity;
    }

    public void setLocalEntity(MongoLocalEntity localEntity) {
	this.localEntity = localEntity;
    }

    public String getComment() {
	return comment;
    }

    public void setComment(String comment) {
	this.comment = comment;
    }
}
