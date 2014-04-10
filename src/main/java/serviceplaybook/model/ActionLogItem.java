package serviceplaybook.model;

import java.util.Date;

public class ActionLogItem {
    private String actionType;
    private String personId;
    private String personName;

    public String getPersonName() {
	return personName;
    }

    public void setPersonName(String personName) {
	this.personName = personName;
    }

    private Date dateTime;

    public String getActionType() {
	return actionType;
    }

    public void setActionType(String actionType) {
	this.actionType = actionType;
    }

    public String getPersonId() {
	return personId;
    }

    public void setPersonId(String personId) {
	this.personId = personId;
    }

    public Date getDateTime() {
	return dateTime;
    }

    public void setDateTime(Date dateTime) {
	this.dateTime = dateTime;
    }
    public String toString() {
	return personName + " - " + actionType + " - " + dateTime;
    }
}
