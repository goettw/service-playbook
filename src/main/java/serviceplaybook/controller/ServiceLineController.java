package serviceplaybook.controller;
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
import org.springframework.web.bind.annotation.ResponseBody;

import serviceplaybook.model.ServiceLine;
import serviceplaybook.service.ServiceCategoryService;
import serviceplaybook.service.ServiceLineService;
import serviceplaybook.web.SessionContext;

@Controller
public class ServiceLineController {
	@Autowired
	private ServiceLineService serviceLineService;
	@Autowired
	private ServiceCategoryService serviceCategoryService;
	@Autowired
	SessionContext sessionContext;

	@RequestMapping(value = "/serviceLinesJSON", method = RequestMethod.GET)
	public  @ResponseBody List<ServiceLine> getserviceLineJson(ModelMap model) {
		//model.addAttribute("serviceLineList", serviceLineService.listServiceLine());
		return serviceLineService.listServiceLine();
	}

	@RequestMapping(value = "/serviceLineList", method = RequestMethod.GET)
	public String getserviceLineList(ModelMap model) {
		model.addAttribute("serviceLineList", serviceLineService.listServiceLine());
		return "serviceLineList";
	}

	@RequestMapping(value = "/serviceLine/delete/{id}", method = RequestMethod.GET)
	public String deleteserviceLineById(@PathVariable String id, ModelMap model) {
		serviceLineService.deleteServiceLine(serviceLineService.findServiceLineById(id));
		return "redirect:/serviceLineList";
	}

	@RequestMapping(value = "/serviceLine/{id}", method = RequestMethod.GET)
	public String getById(@PathVariable String id, ModelMap model) {
		ServiceLine serviceLine = serviceLineService.findServiceLineById(id);
		model.addAttribute("serviceLine", serviceLine);
		model.addAttribute("serviceLineId", serviceLine.getId());
		model.addAttribute("serviceCategoryList",serviceCategoryService.findServiceCategoryByServiceLine(serviceLine.getId()));
		
		model.addAttribute("serviceLineList",serviceLineService.listServiceLine());		
			
		model.addAttribute("sessionContext", sessionContext);
			
			
		
		return "serviceLine";
	}

	@RequestMapping(value = "/serviceLine/new", method = RequestMethod.GET)
	public String create(ModelMap model) {
		model.addAttribute("serviceLine", new ServiceLine());
		return "serviceLineEdit";
	}

	@RequestMapping(value = "/serviceLineEdit/{id}", method = RequestMethod.GET)
	public String editById(@PathVariable String id, ModelMap model) {
		model.addAttribute("serviceLine", serviceLineService.findServiceLineById(id));
		return "serviceLineEdit";
	}

	@RequestMapping(value = "/serviceLineEdit/submit", method = RequestMethod.POST)
	public String serviceLineEditSubmit(@RequestParam String action, @ModelAttribute ServiceLine serviceLine, ModelMap model) {
		System.out.println(action);
		if (action.equals("Save")) {
			if (StringUtils.hasText(serviceLine.getId())) {
				serviceLineService.updateServiceLine(serviceLine);
			} else {
				serviceLineService.addServiceLine(serviceLine);
			}
		}
		if (serviceLine.getId() == null || serviceLine.getId().equals(""))
			return "redirect:/serviceLineList";
		return "redirect:/serviceLine" + serviceLine.getId();
	}
}
