package serviceplaybook.web;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import serviceplaybook.controller.ServiceNavigator;
import serviceplaybook.model.ServiceCategory;
import serviceplaybook.model.ServiceLine;
import serviceplaybook.model.ServiceOffer;
import serviceplaybook.model.ServicePlaybookDescription;
import serviceplaybook.service.AdminService;

public class SessionContext implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4841375616956625352L;
	private ServiceCategory serviceCategory;
	private ServiceLine serviceLine;
	private ServiceOffer serviceOffer;
	
	private ServiceNavigator serviceNavigator;
	
@Autowired
	
	public ServiceNavigator getServiceNavigator() {
		return serviceNavigator;
	}
	public void setServiceNavigator(ServiceNavigator serviceNavigator) {
		this.serviceNavigator = serviceNavigator;
	}
	public ServiceCategory getServiceCategory() {
		return serviceCategory;
	}
	public void setServiceCategory(ServiceCategory serviceCategory) {
		this.serviceCategory = serviceCategory;
	}
	public ServiceLine getServiceLine() {
		return serviceLine;
	}
	public void setServiceLine(ServiceLine serviceLine) {
		this.serviceLine = serviceLine;
	}
	public ServiceOffer getServiceOffer() {
		return serviceOffer;
	}
	public void setServiceOffer(ServiceOffer serviceOffer) {
		this.serviceOffer = serviceOffer;
	}

}
