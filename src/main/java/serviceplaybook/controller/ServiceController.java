package serviceplaybook.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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

import serviceplaybook.model.ActionLogItem;
import serviceplaybook.model.BigPlayItem;
import serviceplaybook.model.FileMeta;
import serviceplaybook.model.MongoLocalEntity;
import serviceplaybook.model.Profile;
import serviceplaybook.model.ServiceOffer;
import serviceplaybook.mongorepo.BigPlayRepository;
import serviceplaybook.mongorepo.ProfileRepository;
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
    @Autowired
    private ProfileRepository profileRepository;

    private static final String FOLDER_IMAGE = "image";

    @RequestMapping(value = "/bigPlayOverview", method = RequestMethod.GET)
    public String bigPlayList(ModelMap model) {
	model.addAttribute("sessionContext", sessionContext);
	model.addAttribute("servicePlaybookDescription", adminService.getServicePlaybookDescription());
	return "bigPlayOverview";
    }

    @RequestMapping(value = "/admin/serviceOfferList", method = RequestMethod.GET)
    public String getServiceOfferList(ModelMap model) {

	List<ServiceOffer> serviceOfferList = serviceOfferService.listServiceOffer();

	ArrayList<ServiceOffer> newServiceList = new ArrayList<ServiceOffer>();
	for (Iterator<ServiceOffer> it = serviceOfferList.iterator(); it.hasNext();) {
	    ServiceOffer serviceOffer = it.next();
	    List<String> bigplays = serviceOffer.getBigPlay();
	    ArrayList<String> newBigplayList = new ArrayList<String>();
	    if (bigplays != null && !bigplays.isEmpty()) {
		for (Iterator<String> it2 = bigplays.iterator(); it2.hasNext();) {
		    String bigplay = it2.next();
		    BigPlayItem bigPlayItem = bigPlayRepository.findOne(bigplay);
		    if (bigPlayItem != null) {
			newBigplayList.add(bigPlayItem.getDisplay());

		    }
		}
		serviceOffer.setBigPlay(newBigplayList);
		
	    }
	    newServiceList.add(serviceOffer);
	}

	model.addAttribute("serviceOfferList", newServiceList);
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

	ArrayList<BigPlayItem> bigPlays = new ArrayList<BigPlayItem>();
	if (serviceOffer.getBigPlay() != null && !serviceOffer.getBigPlay().isEmpty()) {
	    for (Iterator<String> it = serviceOffer.getBigPlay().iterator(); it.hasNext();) {
		BigPlayItem bigPlayItem = bigPlayRepository.findOne(it.next());
		if (bigPlayItem != null) {
		    bigPlays.add(bigPlayItem);
		}
	    }
	}
	model.addAttribute("bigPlays", bigPlays);
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

	    ActionLogItem actionLogItem = new ActionLogItem();
	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    String userId = null;
	    if (principal instanceof UserDetails) {
		userId = ((UserDetails) principal).getUsername();
	    } else {
		userId = principal.toString();
	    }
	    if (userId != null) {
		Profile profile = profileRepository.findOne(userId);
		if (profile != null) {
		    actionLogItem.setPersonId(userId);
		    actionLogItem.setPersonName(profile.getDisplayName());
		    actionLogItem.setDateTime(Calendar.getInstance().getTime());
		}
	    }

	    List<ActionLogItem> actionLog = null;

	    if (StringUtils.hasText(serviceOffer.getId())) {
		ServiceOffer storedServiceOffer = serviceOfferService.findServiceOfferById(serviceOffer.getId());
		if (storedServiceOffer != null) {
		    actionLog = storedServiceOffer.getActionLog();
		    if (actionLog == null)
			actionLog = new ArrayList<ActionLogItem>();
		} else {
		    actionLog = new ArrayList<ActionLogItem>();
		}
		actionLogItem.setActionType("update");
		actionLog.add(0, actionLogItem);
		serviceOffer.setActionLog(actionLog);
		serviceOfferService.updateServiceOffer(serviceOffer);
	    } else {
		actionLog = new ArrayList<ActionLogItem>();
		actionLogItem.setActionType("create");
		actionLog.add(actionLogItem);
		serviceOffer.setActionLog(actionLog);
		serviceOfferService.addServiceOffer(serviceOffer);
	    }
	}
	if (serviceOffer.getId() == null || serviceOffer.getId().equals(""))
	    return "redirect:/admin/serviceOfferList";
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
