package serviceplaybook.model;
/*
 * { 
 * label: 'test1', 
 * summary: 'Dieser Service geht sowas von ab', 
 * whyEMC : {
 * 	ul : [ 
 * 	{li: 'item1'} , 
 * 	{li: 'item2'}]
 * }
 * }
 */
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import serviceplaybook.model.type.Contact;
import serviceplaybook.model.type.Link;
import serviceplaybook.model.type.RTItem;
@Document
public class ServiceOffer {
	@Id
	private String id;
	private String label;
	private String summary;
	private String whyEMC;
	private String addedValue;
	private String dealValue;
	private String serviceCategory;
	

	private ArrayList<String> bigPlay;
	public ArrayList<String> getBigPlay() {
		return bigPlay;
	}
	public void setBigPlay(ArrayList<String> bigPlay) {
		this.bigPlay = bigPlay;
	}
	private String price;
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getServiceCategory() {
		return serviceCategory;
	}
	public void setServiceCategory(String serviceCategory) {
		this.serviceCategory = serviceCategory;
	}
	private List<Link> caseStudies;
	private List<Link> salesCollateral;
	private List<Contact> emcContacts;
	private List<Link> relatedInformation;

	
	public List<Link> getRelatedInformation() {
		return relatedInformation;
	}
	public void setRelatedInformation(List<Link> relatedInformation) {
		this.relatedInformation = relatedInformation;
	}
	public String getDealValue() {
		return dealValue;
	}
	public void setDealValue(String dealValue) {
		this.dealValue = dealValue;
	}
	
	
	public List<Link> getSalesCollateral() {
		return salesCollateral;
	}
	public void setSalesCollateral(List<Link> salesCollateral) {
		this.salesCollateral = salesCollateral;
	}
	public List<Contact> getEmcContacts() {
		return emcContacts;
	}
	public void setEmcContacts(List<Contact> emcContacts) {
		this.emcContacts = emcContacts;
	}
	public List<Link> getCaseStudies() {
		return caseStudies;
	}
	public void setCaseStudies(List<Link> caseStudies) {
		this.caseStudies = caseStudies;
	}
	public String getAddedValue() {
		return addedValue;
	}
	
	public String getAddedValueAsHTML() {
		return RTItem.convertToHtml(addedValue);
	}
	public void setAddedValue(String addedValue) {
		this.addedValue = addedValue;
	}
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

	public String getWhyEMCAsHTML() {
		return RTItem.convertToHtml(whyEMC);
	}
	
	public String getWhyEMC() {
		return whyEMC;
	}
	public void setWhyEMC(String whyEMC) {
		this.whyEMC = whyEMC;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
}

