package serviceplaybook.controller;
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
import serviceplaybook.service.ServiceCategoryService;
import serviceplaybook.service.ServiceLineService;
import serviceplaybook.service.ServiceOfferService;

@Controller

public class ServiceCategoryController {
	@Autowired
	private ServiceCategoryService serviceCategoryService;
	@Autowired
	private ServiceLineService serviceLineService;
	@Autowired
	private ServiceOfferService serviceOfferService;

	@RequestMapping(value = "/serviceCategoryList", method = RequestMethod.GET)
	public String getserviceCategoryList(ModelMap model) {
		model.addAttribute("serviceCategoryList", serviceCategoryService.listServiceCategory());
		return "serviceCategoryList";
	}

	@RequestMapping(value = "/serviceCategory/delete/{id}", method = RequestMethod.GET)
	public String deleteserviceCategoryById(@PathVariable String id, ModelMap model) {
		serviceCategoryService.deleteServiceCategory(serviceCategoryService.findServiceCategoryById(id));
		return "redirect:/serviceCategoryList";
	}
	 
	@RequestMapping(value = "/serviceCategory/{id}", method = RequestMethod.GET)
	public String getById(@PathVariable String id, ModelMap model) {
		ServiceCategory serviceCategory = serviceCategoryService.findServiceCategoryById(id);
		model.addAttribute("serviceCategory", serviceCategory);
		model.addAttribute("serviceOfferList",serviceOfferService.findServiceOfferByCategory(id));
		
		model.addAttribute("serviceLine", serviceLineService.findServiceLineById(serviceCategory.getServiceLine()));
		if (serviceCategory != null) {
			ServiceLine serviceLine = serviceLineService.findServiceLineById(serviceCategory.getServiceLine());
			
			model.addAttribute("serviceLineId", serviceLine.getId());
			model.addAttribute("serviceCategoryList",serviceCategoryService.findServiceCategoryByServiceLine(serviceLine.getId()));
			model.addAttribute("serviceLineList",serviceLineService.listServiceLine());
			model.addAttribute("serviceOfferList",serviceOfferService.findServiceOfferByCategory(serviceCategory.getId()));
			
		}
		
		return "serviceCategory";
	}

	@RequestMapping(value = "/serviceCategory/new", method = RequestMethod.GET)
	public String create(ModelMap model) {
		model.addAttribute("serviceCategory", new ServiceCategory()); 
		model.addAttribute("serviceLineList",serviceLineService.listServiceLine());
		return "serviceCategoryEdit";
	}

	@RequestMapping(value = "/serviceCategoryEdit/{id}", method = RequestMethod.GET)
	public String editById(@PathVariable String id, ModelMap model) {
		model.addAttribute("serviceCategory", serviceCategoryService.findServiceCategoryById(id));
		model.addAttribute("serviceLineList",serviceLineService.listServiceLine());
		return "serviceCategoryEdit";
	}

	@RequestMapping(value = "/serviceCategoryEdit/submit", method = RequestMethod.POST)
	public String serviceCategoryEditSubmit(@RequestParam String action, @ModelAttribute ServiceCategory serviceCategory, ModelMap model) {
		System.out.println(action);
		if (action.equals("Save")) {
			if (StringUtils.hasText(serviceCategory.getId())) { 
				serviceCategoryService.updateServiceCategory(serviceCategory);
			} else {
				serviceCategoryService.addServiceCategory(serviceCategory);
			}
		}
		if (serviceCategory.getId() == null || serviceCategory.getId().equals(""))
			return "redirect:/serviceCategoryList/"; 
		return "redirect:/serviceCategory/" + serviceCategory.getId();
	}
}
