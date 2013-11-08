package serviceplaybook.model;

import org.hibernate.validator.constraints.NotEmpty;

public class Comment extends ActionLogItem {
    private String id;

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    @NotEmpty
    private String comment;
    @NotEmpty
    private String headline;

    public String getHeadline() {
	return headline;
    }

    public void setHeadline(String headline) {
	this.headline = headline;
    }

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
