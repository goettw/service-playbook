package serviceplaybook.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import serviceplaybook.model.FileMeta;
import serviceplaybook.model.MongoLocalEntity;
import serviceplaybook.model.ServiceOffer;
import serviceplaybook.mongorepo.BigPlayRepository;
import serviceplaybook.service.AdminService;
import serviceplaybook.service.FileFormService;
import serviceplaybook.service.ServiceOfferService;
import serviceplaybook.web.SessionContext;

import com.mongodb.gridfs.GridFSDBFile;

@Controller
public class ServiceController {

	@Autowired
	private ServiceOfferService serviceOfferService;
	@Autowired
	private BigPlayRepository bigPlayRepository;
	@Autowired
	private SessionContext sessionContext;
	@Autowired
	FileFormService fileFormService;
	@Autowired
	AdminService adminService;
	private static final String FOLDER_IMAGE = "image";

	@RequestMapping(value = "/bigPlayOverview", method = RequestMethod.GET)
	public String bigPlayList(ModelMap model) {
		model.addAttribute("sessionContext", sessionContext);
		model.addAttribute("servicePlaybookDescription", adminService.getServicePlaybookDescription());
		return "bigPlayOverview";
	}

	@RequestMapping(value = "/admin/serviceOfferList", method = RequestMethod.GET)
	public String getServiceOfferList(ModelMap model) {
		model.addAttribute("serviceOfferList", serviceOfferService.listServiceOffer());
		return "serviceofferList";
	}

	@RequestMapping(value = "author/serviceOffer/delete/{id}", method = RequestMethod.GET)
	public String deleteServiceOfferById(@PathVariable String id, ModelMap model) {
		serviceOfferService.deleteServiceOffer(serviceOfferService.findServiceOfferById(id));
		return "redirect:/admin/serviceOfferList";
	}

	@RequestMapping(value = "/serviceOffer/{id}", method = RequestMethod.GET)
	public String getById(@PathVariable String id, ModelMap model, HttpServletRequest request) {
		ServiceOffer serviceOffer = serviceOfferService.findServiceOfferById(id);
		model.addAttribute("serviceOffer", serviceOffer);
		model.addAttribute("sessionContext", sessionContext);
		model.addAttribute("subtitle", serviceOffer.getLabel());
		model.addAttribute("editUrl", "/author/serviceOffer/edit/" + id);
		String imageUrl = getImageUrl(request, id);
		if (imageUrl != null)
			model.addAttribute("imageUrl", imageUrl);
		return "serviceoffer";
	}

	@RequestMapping(value = "author/serviceOffer/new", method = RequestMethod.GET)
	public String create(ModelMap model) {
		List<String> statusList = new LinkedList<String>();
		statusList.add("draft");
		statusList.add("released");
		model.addAttribute("serviceOffer", new ServiceOffer());
		model.addAttribute("statusList", statusList);
		model.addAttribute("bigPlayList", bigPlayRepository.findAll(new Sort(Direction.ASC, "sortOrderNo", "level1", "level2")));
		return "serviceofferEdit";
	}

	@RequestMapping(value = "author/serviceOffer/uploadImage/{id}", method = RequestMethod.POST)
	public @ResponseBody
	FileMeta uploadImage(@PathVariable String id, MultipartHttpServletRequest request, HttpServletResponse response) {
		MongoLocalEntity mongoLocalEntity = new MongoLocalEntity(serviceOfferService.getCollectionName(), id, FOLDER_IMAGE);
		FileMeta fileMeta = fileFormService.upload(mongoLocalEntity, request, response, true);
		fileMeta.setUrl(request.getContextPath() + "/file-controller/get/" + fileMeta.getId() + "/" + fileMeta.getFileName());
		return fileMeta;
	}

	@RequestMapping(value = "author/serviceOffer/edit/{id}", method = RequestMethod.GET)
	public String editById(@PathVariable String id, ModelMap model, HttpServletRequest request) {
		List<String> statusList = new LinkedList<String>();
		statusList.add("draft");
		statusList.add("released");
		ServiceOffer serviceOffer = serviceOfferService.findServiceOfferById(id);
		model.addAttribute("serviceOffer", serviceOffer);
		model.addAttribute("statusList", statusList);
		model.addAttribute("bigPlayList", bigPlayRepository.findAll(new Sort(Direction.ASC, "sortOrderNo", "level1", "level2")));
		String imageUrl = getImageUrl(request, id);
		if (imageUrl != null)
			model.addAttribute("imageUrl", imageUrl);
		return "serviceofferEdit";
	}

	@RequestMapping(value = "author/serviceOffer/submit", method = RequestMethod.POST)
	public String serviceOfferEditSubmit(@RequestParam String action, @Valid @ModelAttribute ServiceOffer serviceOffer, BindingResult bindingResult,
			ModelMap model) {
		if (action.equals("Save")) {
			if (bindingResult.hasErrors())
				return "serviceofferEdit";
			if (StringUtils.hasText(serviceOffer.getId())) {
				serviceOfferService.updateServiceOffer(serviceOffer);
			} else {
				serviceOfferService.addServiceOffer(serviceOffer);
			}
		}
		if (serviceOffer.getId() == null || serviceOffer.getId().equals(""))
			return "redirect:/serviceOfferList";
		model.addAttribute("editUrl", "author/serviceOffer/edit/" + serviceOffer.getId());
		return "redirect:/serviceOffer/" + serviceOffer.getId();
	}

	private String getImageUrl(HttpServletRequest request, String serviceOfferId) {
		List<GridFSDBFile> files = fileFormService.findFiles(new MongoLocalEntity(serviceOfferService.getCOLLECTION_NAME(), serviceOfferId, FOLDER_IMAGE));
		if (files.size() > 0) {
			GridFSDBFile file = files.get(0);
			return request.getContextPath() + "/file-controller/get/" + file.getId().toString() + "/" + file.getFilename();
		}
		return null;
	}
}
