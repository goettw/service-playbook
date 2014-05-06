package serviceplaybook.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.core.GrantedAuthority;
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

import serviceplaybook.controller.viewmodel.CommentViewBean;
import serviceplaybook.model.ActionLogItem;
import serviceplaybook.model.BigPlayItem;
import serviceplaybook.model.Comment;
import serviceplaybook.model.FileMeta;
import serviceplaybook.model.MongoLocalEntity;
import serviceplaybook.model.Profile;
import serviceplaybook.model.ServiceOffer;
import serviceplaybook.mongorepo.BigPlayRepository;
import serviceplaybook.mongorepo.CommentRepository;
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
    @Autowired
    private CommentRepository commentRepository;

    private static final String FOLDER_IMAGE = "image";

    @RequestMapping(value = "/bigPlayOverview", method = RequestMethod.GET)
    public String bigPlayList(ModelMap model) {
	model.addAttribute("sessionContext", sessionContext);
	model.addAttribute("servicePlaybookDescription", adminService.getServicePlaybookDescription());
	return "bigPlayOverview";
    }

    @RequestMapping(value = "/admin/serviceOfferList", method = RequestMethod.GET)
    public String getServiceOfferListAuthor(ModelMap model) {
	prepareServiceList(model, "label", Direction.ASC);
	return "serviceofferListAuthor";
    }

    @RequestMapping(value = "/auth/serviceList", method = RequestMethod.GET)
    public String getServiceOfferList(ModelMap model) {
	prepareServiceList(model, "label", Direction.ASC);
	return "serviceofferList";
    }

    @RequestMapping(value = "/auth/serviceListByUpdate", method = RequestMethod.GET)
    public String getServiceOfferListByUpdate(ModelMap model) {
	prepareServiceList(model, "actionLog.dateTime", Direction.DESC);
	return "serviceofferList";
    }

    private void prepareServiceList(ModelMap model, String sortBy, Direction direction) {
	List<ServiceOffer> serviceOfferList = serviceOfferService.listServiceOffer(sortBy, direction);
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
    }

    @RequestMapping(value = "author/serviceOffer/delete/{id}", method = RequestMethod.GET)
    public String deleteServiceOfferById(@PathVariable String id, ModelMap model) {
	serviceOfferService.deleteServiceOffer(serviceOfferService.findServiceOfferById(id));
	return "redirect:/admin/serviceOfferList";
    }

    @RequestMapping(value = "/serviceOffer/{id}", method = RequestMethod.GET)
    public String getById(@PathVariable String id, ModelMap model, HttpServletRequest request) {
	ServiceOffer serviceOffer = serviceOfferService.findServiceOfferById(id);

	serviceOffer.setActionLog(cleanUpActionLog(serviceOffer.getActionLog()));
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

	MongoLocalEntity mongoLocalEntity = new MongoLocalEntity("ServiceOffer", id, MongoLocalEntity.FOLDER_COMMENT);

	List<Comment> comments = commentRepository.findCommentsByLocalEntity(mongoLocalEntity, new Sort(Direction.DESC, "dateTime"));
	List<CommentViewBean> commentViewBeans = new ArrayList<CommentViewBean>();
	for (Iterator<Comment> it = comments.iterator(); it.hasNext();) {
	    Comment comment = it.next();
	
	    CommentViewBean commentViewBean = new CommentViewBean(comment);
	    commentViewBean.setAllowDelete(allowedToDelete(comment));
	    commentViewBeans.add(commentViewBean);
	}
	model.addAttribute("comments", commentViewBeans);

	Comment newComment = new Comment();
	newComment.setLocalEntity(mongoLocalEntity);
	model.addAttribute("comment", newComment);
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
	model.addAttribute("contacts", serviceOffer.getEmcContacts());
	String imageUrl = getImageUrl(request, id);
	if (imageUrl != null)
	    model.addAttribute("imageUrl", imageUrl);
	return "serviceofferEdit";
    }

    @RequestMapping(value = "auth/serviceOffer/submitComment", method = RequestMethod.POST)
    public String serviceOfferSubmitComment(@RequestParam String action, @Valid @ModelAttribute Comment comment, BindingResult bindingResult, ModelMap model) {
	if (bindingResult.hasErrors()) {
	    ServiceOffer serviceOffer = serviceOfferService.findServiceOfferById(comment.getLocalEntity().getId());
	    model.addAttribute("serviceOffer", serviceOffer);
	    MongoLocalEntity mongoLocalEntity = new MongoLocalEntity("ServiceOffer", comment.getLocalEntity().getId(), MongoLocalEntity.FOLDER_COMMENT);

	    List<Comment> comments = commentRepository.findCommentsByLocalEntity(mongoLocalEntity, new Sort(Direction.DESC, "dateTime"));
	    model.addAttribute("comments", comments);
	    return "serviceoffer";
	}
	ActionLogItem actionLogItem = prepareActionLog();
	comment.setPersonId(actionLogItem.getPersonId());
	comment.setPersonName(actionLogItem.getPersonName());
	comment.setDateTime(actionLogItem.getDateTime());
	commentRepository.save(comment);
	return "redirect:/serviceOffer/" + comment.getLocalEntity().getId();
    }

    private boolean allowedToDelete(Comment comment) {

	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	boolean allow = false;
	if (principal instanceof UserDetails) {
	    Collection<GrantedAuthority> grantedAuthorities = (Collection<GrantedAuthority>)((UserDetails) principal).getAuthorities();
	    
	    for (Iterator<GrantedAuthority> it = grantedAuthorities.iterator();it.hasNext();){
		GrantedAuthority authority = (GrantedAuthority)it.next();
		if (authority.getAuthority().equals("ROLE_Administrator"))
			return true;
	    }
	    String userId = ((UserDetails) principal).getUsername();
	    if (userId.equals(comment.getPersonId()))
		allow = true;
	}
	return allow;
    }

    

    @RequestMapping(value = "auth/serviceOffer/deleteComment/{id}", method = RequestMethod.GET)
    public String deleteComment(@PathVariable String id, ModelMap model, HttpServletRequest request) {
	Comment comment = commentRepository.findOne(id);
	if (comment == null)
	    return "serviceOffer";

	if (allowedToDelete(comment))
	    commentRepository.delete(comment);

	String serviceId = comment.getLocalEntity().getId();

	return "redirect:/serviceOffer/" + serviceId;
    }

    private ActionLogItem prepareActionLog() {
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
	return actionLogItem;
    }

    private void printActionLog (List <ActionLogItem> actionLog) {
	int i = 0;
	if (actionLog == null) return;
	for (Iterator<ActionLogItem> it = actionLog.iterator();it.hasNext();) {
	    ActionLogItem item = it.next();
	    System.out.println("item " + i++ + ": " + item);
	}	
    }
    
    @RequestMapping(value = "author/serviceOffer/submit", method = RequestMethod.POST)
    public String serviceOfferEditSubmit(@RequestParam String action, @Valid @ModelAttribute ServiceOffer serviceOffer, BindingResult bindingResult,
	    ModelMap model) {
	if (action.equals("Save")) {
	    if (bindingResult.hasErrors())
		return "serviceofferEdit";

	    // Create Action Log Item
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

	    // now, put into the action log list
	    List<ActionLogItem> actionLog = null;

	    if (StringUtils.hasText(serviceOffer.getId())) { // already have an
							     // id (service
							     // exists already
							     // in database)
		// load it to get the existing actionLog
		actionLogItem.setActionType("update");
		ServiceOffer storedServiceOffer = serviceOfferService.findServiceOfferById(serviceOffer.getId());
		if (storedServiceOffer != null) {
		    actionLog = storedServiceOffer.getActionLog();
		    System.out.println ("original actionLog");
		    printActionLog(actionLog);
		    if (actionLog == null)
			actionLog = new ArrayList<ActionLogItem>();
		    else
			actionLog = cleanUpActionLog(actionLog);
		   
		    System.out.println("actionLogCleaned");
		    printActionLog(actionLog);

		    if (!actionLog.isEmpty() && !differentEnoughToSave(actionLogItem, actionLog.get(0))) {
			System.out.println("remove head of actionlog");
			actionLog.remove(0);
		    }

		} else {
		    actionLog = new ArrayList<ActionLogItem>();
		}

		
		actionLog.add(0, actionLogItem);
		serviceOffer.setActionLog(actionLog);
		    System.out.println("new actionLog ");
		    printActionLog(actionLog);

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

    private boolean differentEnoughToSave(ActionLogItem item1, ActionLogItem item2) {
	boolean differentEnough = !(sameDate(item1, item2) && samePerson(item1, item2) && item1.getActionType().equals(item2.getActionType())); 
	System.out.println ("differentEnoughToSave ?" + " item1 " + item1 + " item2 " + item2 + " -> " + differentEnough);
	return differentEnough;
    }

    private List<ActionLogItem> cleanUpActionLog(List<ActionLogItem> actionLog) {
	System.out.println("cleanUpActionLog");
	if (actionLog == null || actionLog.isEmpty())
	    return actionLog;

	List<ActionLogItem> newActionLog = new ArrayList<ActionLogItem>();
	ActionLogItem lastActionLogItemSaved = null;
	for (Iterator<ActionLogItem> it = actionLog.iterator(); it.hasNext();) {
	    ActionLogItem actionLogItem = it.next();
	    boolean storeToCleanList = lastActionLogItemSaved == null || differentEnoughToSave(lastActionLogItemSaved, actionLogItem);

	    if (storeToCleanList) {
		Profile profile = profileRepository.findOne(actionLogItem.getPersonId());
		if (profile != null)
		    actionLogItem.setPersonName(profile.getDisplayName());

		newActionLog.add(actionLogItem);
		lastActionLogItemSaved = actionLogItem;
	    }
	}
	return newActionLog;
    }

    private boolean samePerson(ActionLogItem item1, ActionLogItem item2) {
	return item1.getPersonId().equals(item2.getPersonId());
    }

    private String toString(ActionLogItem actionLogItem) {
	Calendar cal = Calendar.getInstance();
	cal.setTime(actionLogItem.getDateTime());
	return actionLogItem.getPersonId() + "-" + cal.get(Calendar.DAY_OF_MONTH) + "." + cal.get(Calendar.MONTH) + "." + cal.get(Calendar.YEAR) + " - "
		+ actionLogItem.getActionType();

    }

    private boolean sameDate(ActionLogItem item1, ActionLogItem item2) {
	Calendar cal1 = Calendar.getInstance();
	Calendar cal2 = Calendar.getInstance();
	cal1.setTime(item1.getDateTime());
	cal2.setTime(item2.getDateTime());
	if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
		&& cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH))
	    return true;
	return false;
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
