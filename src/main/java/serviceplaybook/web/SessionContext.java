package serviceplaybook.web;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import serviceplaybook.controller.ServiceNavigator;
import serviceplaybook.model.ServiceOffer;

public class SessionContext implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4841375616956625352L;


	private ServiceNavigator serviceNavigator;
	
@Autowired
	
	public ServiceNavigator getServiceNavigator() {
		return serviceNavigator;
	}
	public void setServiceNavigator(ServiceNavigator serviceNavigator) {
		this.serviceNavigator = serviceNavigator;
	}
	


}
