package serviceplaybook.web;

import serviceplaybook.controller.ServiceNavigator;
import serviceplaybook.model.ServiceCategory;
import serviceplaybook.model.ServiceLine;
import serviceplaybook.model.ServiceOffer;

public class SessionContext {
	private ServiceCategory serviceCategory;
	private ServiceLine serviceLine;
	private ServiceOffer serviceOffer;
	
	private ServiceNavigator serviceNavigator;
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
