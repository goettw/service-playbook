package serviceplaybook.controller;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import serviceplaybook.model.ServiceCategory;
import serviceplaybook.model.ServiceLine;
import serviceplaybook.model.ServiceOffer;
import serviceplaybook.mongorepo.BigPlayRepository;
import serviceplaybook.service.ServiceCategoryService;
import serviceplaybook.service.ServiceLineService;
import serviceplaybook.service.ServiceOfferService;
import serviceplaybook.web.SessionContext;

@Controller
public class ServiceController {
	@Autowired
	private ServiceOfferService serviceOfferService;
	@Autowired
	private ServiceCategoryService serviceCategoryService;
	@Autowired
	private ServiceLineService serviceLineService;
	@Autowired
	private BigPlayRepository bigPlayRepository;
	@Autowired
	private SessionContext sessionContext;

	@RequestMapping(value = "/bigPlayOverview", method = RequestMethod.GET)
	public String bigPlayList (ModelMap model) {
		model.addAttribute("sessionContext", sessionContext);
		
		model.addAttribute("serviceLineList",serviceLineService.listServiceLine());

		return "bigPlayOverview";
	}
	
	@RequestMapping(value = "/serviceOfferList", method = RequestMethod.GET)
	public String getServiceOfferList(ModelMap model) {
		model.addAttribute("serviceOfferList", serviceOfferService.listServiceOffer());
		return "serviceofferList";
	}

	@RequestMapping(value = "/serviceOffer/delete/{id}", method = RequestMethod.GET)
	public String deleteServiceOfferById(@PathVariable String id, ModelMap model) {
		serviceOfferService.deleteServiceOffer(serviceOfferService.findServiceOfferById(id));
		return "redirect:/serviceOfferList";
	}

	@RequestMapping(value = "/serviceOffer/{id}", method = RequestMethod.GET)
	public String getById(@PathVariable String id, ModelMap model) {
		ServiceOffer serviceOffer = serviceOfferService.findServiceOfferById(id);
		model.addAttribute("serviceOffer", serviceOffer);
		ServiceCategory serviceCategory = serviceCategoryService.findServiceCategoryById(serviceOffer.getServiceCategory());

		if (serviceCategory != null) {
			ServiceLine serviceLine = serviceLineService.findServiceLineById(serviceCategory.getServiceLine());
			model.addAttribute("serviceCategoryId", serviceCategory.getId());
			model.addAttribute("serviceLineId", serviceLine.getId());
			model.addAttribute("serviceCategoryList", serviceCategoryService.findServiceCategoryByServiceLine(serviceLine.getId()));
			model.addAttribute("serviceLineList", serviceLineService.listServiceLine());
			model.addAttribute("serviceOfferList", serviceOfferService.findServiceOfferByCategory(serviceCategory.getId()));

		}
		sessionContext.setServiceOffer(serviceOffer);
		model.addAttribute("sessionContext", sessionContext);
		model.addAttribute("subtitle", serviceOffer.getLabel());
		return "serviceoffer";
	}

	@RequestMapping(value = "/serviceOffer/new", method = RequestMethod.GET)
	public String create(ModelMap model) {
		List<String> statusList = new LinkedList<String>();
		statusList.add("draft");
		statusList.add("released");

		model.addAttribute("serviceOffer", new ServiceOffer());
		model.addAttribute("serviceCategoryList", serviceCategoryService.listServiceCategory());
		model.addAttribute("statusList", statusList);
		model.addAttribute("bigPlayList",bigPlayRepository.findAll());

		return "serviceofferEdit";
	}

	@RequestMapping(value = "/serviceOfferEdit/{id}", method = RequestMethod.GET)
	public String editById(@PathVariable String id, ModelMap model) {
		List<String> statusList = new LinkedList<String>();
		statusList.add("draft");
		statusList.add("released");
		model.addAttribute("serviceOffer", serviceOfferService.findServiceOfferById(id));
		model.addAttribute("serviceCategoryList", serviceCategoryService.listServiceCategory());
		model.addAttribute("statusList", statusList);
		model.addAttribute("bigPlayList",bigPlayRepository.findAll());
		return "serviceofferEdit";
	}

	@RequestMapping(value = "/serviceOfferEdit/submit", method = RequestMethod.POST)
	public String serviceOfferEditSubmit(@RequestParam String action, @ModelAttribute ServiceOffer serviceOffer, ModelMap model) {
		System.out.println(action);
		if (action.equals("Save")) {
			if (StringUtils.hasText(serviceOffer.getId())) {
				serviceOfferService.updateServiceOffer(serviceOffer);
			} else {
				serviceOfferService.addServiceOffer(serviceOffer);
			}
		}
		if (serviceOffer.getId() == null || serviceOffer.getId().equals(""))
			return "redirect:/serviceOfferList";
		return "redirect:/serviceOffer/" + serviceOffer.getId();
	}

}
