package serviceplaybook.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.mongodb.gridfs.GridFSDBFile;

import serviceplaybook.model.FileMeta;
import serviceplaybook.model.MongoLocalEntity;
import serviceplaybook.model.ServiceCategory;
import serviceplaybook.model.ServiceLine;
import serviceplaybook.model.ServiceOffer;
import serviceplaybook.mongorepo.BigPlayRepository;
import serviceplaybook.service.FileFormService;
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
	@Autowired
	FileFormService fileFormService;
	private static final String FOLDER_IMAGE = "image";

	@RequestMapping(value = "/bigPlayOverview", method = RequestMethod.GET)
	public String bigPlayList(ModelMap model) {
		model.addAttribute("sessionContext", sessionContext);
		model.addAttribute("serviceLineList", serviceLineService.listServiceLine());
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
	public String getById(@PathVariable String id, ModelMap model, HttpServletRequest request) {
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
			if (getImageId(id) != null)
				model.addAttribute("imageUrl", request.getContextPath()+"/file-controller/get/" + getImageId(id));
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
		model.addAttribute("bigPlayList", bigPlayRepository.findAll());
		return "serviceofferEdit";
	}

	@RequestMapping(value = "/serviceOffer/uploadImage/{id}", method = RequestMethod.POST)
	public @ResponseBody
	FileMeta uploadImage(@PathVariable String id, MultipartHttpServletRequest request, HttpServletResponse response) {
		MongoLocalEntity mongoLocalEntity = new MongoLocalEntity(serviceOfferService.getCollectionName(), id, FOLDER_IMAGE);
		FileMeta fileMeta = fileFormService.upload(mongoLocalEntity, request, response, true);
		fileMeta.setUrl(request.getContextPath() + "/file-controller/get/" + fileMeta.getId());
		return fileMeta;
	}

	@RequestMapping(value = "/serviceOfferEdit/{id}", method = RequestMethod.GET)
	public String editById(@PathVariable String id, ModelMap model, HttpServletRequest request) {
		List<String> statusList = new LinkedList<String>();
		statusList.add("draft");
		statusList.add("released");
		ServiceOffer serviceOffer = serviceOfferService.findServiceOfferById(id);
		model.addAttribute("serviceOffer", serviceOffer);
		model.addAttribute("serviceCategoryList", serviceCategoryService.listServiceCategory());
		model.addAttribute("statusList", statusList);
		model.addAttribute("bigPlayList", bigPlayRepository.findAll());
		if (getImageId(id) != null)
			model.addAttribute("imageUrl", request.getContextPath()+"/file-controller/get/" + getImageId(id));
		sessionContext.setServiceOffer(serviceOffer);
		return "serviceofferEdit";
	}

	@RequestMapping(value = "/serviceOfferEdit/submit", method = RequestMethod.POST)
	public String serviceOfferEditSubmit(@RequestParam String action, @ModelAttribute ServiceOffer serviceOffer, ModelMap model) {

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

	private String getImageId(String serviceOfferId) {
		List<GridFSDBFile> files = fileFormService.findFiles(new MongoLocalEntity(serviceOfferService.getCOLLECTION_NAME(), serviceOfferId, FOLDER_IMAGE));
		
		if (files.size() > 0)
			return files.get(0).getId().toString();
		return null;

	}
}
