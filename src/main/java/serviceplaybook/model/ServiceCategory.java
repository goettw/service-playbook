package serviceplaybook.model;

import java.util.List;

import serviceplaybook.model.type.Link;
import serviceplaybook.model.type.RTItem;

public class ServiceCategory {
	private String id;
	private String serviceLine;
	public String getServiceLine() {
		return serviceLine;
	}

	public void setServiceLine(String serviceLine) {
		this.serviceLine = serviceLine;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String label;
	private String summary;
	private String qualification;
	private String goal;
	private String challenges;
	private List<Link> relatedInformation;
	

	public String getQualification() {
		return qualification;
	}
	
	public String getQualificationAsHTML () {
		return RTItem.convertToHtml(qualification);
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public String getGoal() {
		return goal;
	}
	public String getGoalAsHTML () {
		return RTItem.convertToHtml(goal);
	}
	
	public String getChallengesAsHTML () {
		return RTItem.convertToHtml(challenges);
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public String getChallenges() {
		return challenges;
	}

	public void setChallenges(String challenges) {
		this.challenges = challenges;
	}

	public List<Link> getRelatedInformation() {
		return relatedInformation;
	}

	public void setRelatedInformation(List<Link> relatedInformation) {
		this.relatedInformation = relatedInformation;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
}
