package serviceplaybook.model;

import serviceplaybook.model.type.RTItem;

public class BigPlayItem {
	public String getLabel() {
		return getDisplay();
	}

	public String getPainPoints() {
		return painPoints;
	}

	public String getPainPointsAsHtml() {
		return RTItem.convertToHtml(painPoints);
	}

	public void setPainPoints(String painPoints) {
		this.painPoints = painPoints;
	}

	String id;
	// initial Value
	int sortOrderNo = 100000;

	public int getSortOrderNo() {
		return sortOrderNo;
	}

	public void setSortOrderNo(int sortOrderNo) {
		this.sortOrderNo = sortOrderNo;
	}

	String level1;
	String level2;
	String display;
	String label;
	String summary;
	String painPoints;
	String vision;
	String customerQualification;

	public String getCustomerQualification() {
		return customerQualification;
	}
	public String getCustomerQualificationAsHtml() {
		return RTItem.convertToHtml(customerQualification);
	}

	public void setCustomerQualification(String customerQualification) {
		this.customerQualification = customerQualification;
	}

	public String getVision() {
		return vision;
	}

	public String getVisionAsHtml() {
		return RTItem.convertToHtml(vision);
	}

	public void setVision(String vision) {
		this.vision = vision;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getDisplay() {
		return getLevel1() + " / " + getLevel2();
	}

	public String getLevel1() {
		return level1;
	}

	public void setLevel1(String level1) {
		this.level1 = level1;
	}

	public String getLevel2() {
		return level2;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setLevel2(String level2) {
		this.level2 = level2;
	}
}
