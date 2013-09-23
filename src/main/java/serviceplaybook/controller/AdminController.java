package serviceplaybook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import serviceplaybook.model.ServicePlaybookDescription;
import serviceplaybook.mongorepo.ServicePlaybookDescriptionRepository;

@Controller
public class AdminController {
	@Autowired
	ServicePlaybookDescriptionRepository servicePlaybookDescriptionRepository;

	@RequestMapping(value = "/admin/servicePlaybookDescription", method = RequestMethod.GET)
	public String getServicePlaybookDescription(ModelMap model) {
		model.put("servicePlaybookDescription", getServicePlaybookDescription());
		model.put("editUrl","/admin/servicePlaybookDescription/edit");
		return "servicePlaybookDescription";
	}
	
	@RequestMapping(value = "/admin/servicePlaybookDescription/edit", method = RequestMethod.GET)
	public String editServicePlaybookDescription(ModelMap model) {
		model.put("servicePlaybookDescription", getServicePlaybookDescription());
		return "servicePlaybookDescriptionEdit";
	}
	
	@RequestMapping(value = "/admin/servicePlaybookDescription/submit", method = RequestMethod.POST)
	public String saveServicePlaybookDescription (@RequestParam String action, @ModelAttribute ServicePlaybookDescription servicePlaybookDescription) {
		servicePlaybookDescriptionRepository.save(servicePlaybookDescription);
		return "redirect:/admin/servicePlaybookDescription";
	}
	
	
	private ServicePlaybookDescription getServicePlaybookDescription() {
		Iterable<ServicePlaybookDescription> it = servicePlaybookDescriptionRepository.findAll();
		if (it == null || it.iterator().hasNext() == false)
			return new ServicePlaybookDescription();
		ServicePlaybookDescription servicePlaybookDescription = it.iterator().next();
		return servicePlaybookDescription;
	}

}
