package serviceplaybook.controller;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import serviceplaybook.model.GrantedAuthorityContainer;
import serviceplaybook.model.Profile;
import serviceplaybook.mongorepo.ProfileRepository;
import serviceplaybook.service.ApplicationMailer;

@Controller
public class SecurityController {
    @Autowired
    private ApplicationMailer mailer;
    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder bCryptPasswordEncoder;

    private List<String> authorityList;// = new ArrayList<String>();

    public List<String> getAuthorityList() {
	return authorityList;
    }

    @Autowired
    public void setAuthorityList(@Value("#{authorities}") List<String> authorityList) {
	this.authorityList = authorityList;
    }

    @RequestMapping(value = "/loginForm", method = RequestMethod.GET)
    public String login(ModelMap model) {
	model.addAttribute("profile", new LoginFormModel());
	return "loginForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(ModelMap model) {
	model.addAttribute("registrationBean", new RegistrationBean());
	return "registrationForm";
    }

    @RequestMapping(value = "/registration/submit", method = RequestMethod.POST)
    public String registrationSubmit(@RequestParam String action, @Valid @ModelAttribute RegistrationBean registrationBean, BindingResult result, ModelMap model, HttpServletRequest request) {

	if (action.equals("register")) {

	    if (result.hasErrors()) {

		return "registrationForm";
	    }

	    if (profileRepository.findOne(registrationBean.getUsername()) != null)
		result.addError(new FieldError("registrationBean", "username", registrationBean.getUsername(), false, new String[] { "username.inUse" }, null,
			"in use"));

	    if (!profileRepository.findProfilesByEmailAddress(registrationBean.getEmailAddress()).isEmpty())
		result.addError(new FieldError("registrationBean", "emailAddress", registrationBean.getEmailAddress(), false,
			new String[] { "emailAddress.inUse" }, null, "in use"));

	    if (result.hasErrors()) {

		return "registrationForm";
	    }

	    String password = UUID.randomUUID().toString();
	    Profile profile = new Profile();

	    profile.setAccountNonExpired(true);
	    profile.setAccountNonLocked(true);
	    profile.setCredentialsNonExpired(true);
	    profile.setFirstName(registrationBean.getFirstName());
	    profile.setLastName(registrationBean.getLastName());
	    profile.setEnabled(true);
	    if (registrationBean.getUsername().equals("Attminn")) {
		profile.setAuthorityValues(authorityList);
	    }
	    else {
		    ArrayList<String> list = new ArrayList<String>();
		    list.add(authorityList.get(0));
		    profile.setAuthorityValues(list);
		
	    }
	    // bCryptPasswordEncoder = new BCryptPasswordEncoder();
	    profile.setPassword(bCryptPasswordEncoder.encode(password));
	    profile.setUsername(registrationBean.getUsername());
	    profile.setEmailAddress(registrationBean.getEmailAddress());
	    // Send a pre-configured mail
	    mailer.sendRegistrationMail(registrationBean.getEmailAddress(), "<HTML> <HEAD/> <BODY>" 
	    	+ "Welcome to the Service Playbook! <br> Your temporary Password for the Username: " + registrationBean.getUsername() + " is: " + password + 
	    	"<br> Please change it as soon as possible using the following link: <a href=\"http://" + 
	    	request.getServerName()+":"+request.getServerPort()+request.getContextPath() + "/profile/edit?id=" + URLEncoder.encode(registrationBean.getUsername()) + "\">Edit Profile</a>" );
	    profileRepository.save(profile);
	    model.addAttribute("success", "true");
	    return "registrationForm";
	} else
	    return "registrationForm";
    }

    /*
     * CRUD methods for profile administration
     */
    @PreAuthorize("hasRole('ROLE_Administrator')")
    @RequestMapping(value = "/profile/new", method = RequestMethod.GET)
    public String profileNew(ModelMap model) {
	Profile profile = new Profile();
	profile.setAccountNonExpired(true);
	profile.setAccountNonLocked(true);
	profile.setCredentialsNonExpired(true);
	profile.setEnabled(true);
	ArrayList<String> list = new ArrayList<String>();
	list.add(authorityList.get(0));
	profile.setAuthorityValues(list);
	model.addAttribute("profile", profile);

	model.addAttribute("authorityList", authorityList);
	return "profileEdit";
    }

    @PreAuthorize("#id == authentication.name")
    @RequestMapping(value = "/profile/edit", method = RequestMethod.GET)
    public String profileEdit(@RequestParam String id, ModelMap model, HttpServletRequest request) {

	Profile profile = profileRepository.findOne(id);
	model.addAttribute("profile", profile);
	model.addAttribute("authorityList", authorityList);
	model.addAttribute("addUrl", request.getContextPath() + "/profile/submit");
	return "profileEdit";
    }

    @PreAuthorize("hasRole('ROLE_Administrator')")
    @RequestMapping(value = "/admin/profile/edit", method = RequestMethod.GET)
    public String profileAdminEdit(@RequestParam String id, ModelMap model, HttpServletRequest request) {

	Profile profile = profileRepository.findOne(id);
	model.addAttribute("profile", profile);
	model.addAttribute("authorityList", authorityList);
	model.addAttribute("addUrl", request.getContextPath() + "/admin/profile/submit");
	return "profileEdit";

    }

    @PreAuthorize("hasRole('ROLE_Administrator')")
    @RequestMapping(value = "/admin/profile/delete", method = RequestMethod.GET)
    public String profileAdminDelete(@RequestParam String id, ModelMap model, HttpServletRequest request) {
	profileRepository.delete(id);
	return "redirect:/profileList";

    }

    /**
     * A helper function for both, admin and standard user profile submit
     * 
     * @param action
     * @param profile
     * @param result
     * @param model
     * @param request
     * @param addUrl
     * @return
     */
    String profileSubmitHelper(String action, Profile profile, BindingResult result, ModelMap model, HttpServletRequest request, String addUrl) {
	for (Iterator<GrantedAuthorityContainer> it = profile.getAuthorities().iterator(); it.hasNext();) {
	    GrantedAuthorityContainer grantedAuthorityContainer = it.next();
	    System.out.println("1:" + grantedAuthorityContainer.getAuthority());
	}

	if (action.equals("Save")) {

	    // if password has changed, we have to encrypt it
	    Profile oldProfile = profileRepository.findOne(profile.getUsername());
	    boolean encryptPassword = true;

	    if (oldProfile != null && oldProfile.getPassword() != null) {
		if (profile.getPassword() == null || profile.getPassword().isEmpty())
		    profile.setPassword(oldProfile.getPassword());
		if (oldProfile.getPassword().equals(profile.getPassword()))
		    encryptPassword = false;
	    }

	    ValidationUtils.rejectIfEmptyOrWhitespace(result, "password", "NotEmpty.profile.password");
	    if (result.hasErrors()) {
		model.addAttribute("authorityList", authorityList);
		model.addAttribute("addUrl", request.getContextPath() + addUrl);
		return "profileEdit";
	    }

	    if (encryptPassword) {
		bCryptPasswordEncoder = new BCryptPasswordEncoder();
		profile.setPassword(bCryptPasswordEncoder.encode(profile.getPassword()));
	    }

	    profile = profileRepository.save(profile);
	} else {
	    return ("redirect:/");
	}
	// model.put("editUrl", "/admin/profile/edit/" + profile.getUsername());
	return "redirect:/profile?id=" + URLEncoder.encode(profile.getUsername());

    }

    @RequestMapping(value = "/admin/profile/submit", method = RequestMethod.POST)
    public String profileAdminSubmit(@RequestParam String action, @Valid @ModelAttribute Profile profile, BindingResult result, ModelMap model,
	    HttpServletRequest request) {
	return profileSubmitHelper(action, profile, result, model, request, "/admin/profile/submit");
    }

    @RequestMapping(value = "/profile/submit", method = RequestMethod.POST)
    public String profileSubmit(@RequestParam String action, @Valid @ModelAttribute Profile profile, BindingResult result, ModelMap model,
	    HttpServletRequest request) {
	// non-admin users are not allowed to set the
	Profile oldProfile = profileRepository.findOne(profile.getUsername());
	if (oldProfile == null)
	    throw new NullPointerException("something strange happened! A non admin user is able to edit a profile that can not be found in the database");
	profile.setAccountNonExpired(oldProfile.isAccountNonExpired());
	profile.setAccountNonLocked(oldProfile.isAccountNonLocked());
	profile.setEnabled(oldProfile.isEnabled());
	profile.setCredentialsNonExpired(oldProfile.isCredentialsNonExpired());
	profile.setAuthorityValues(oldProfile.getAuthorityValues());
	return profileSubmitHelper(action, profile, result, model, request, "/profile/submit");
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profileView(@RequestParam String id, ModelMap model) {
	Profile profile = profileRepository.findOne(id);
	model.addAttribute("profile", profile);

	
	return "profile";
    }

    @RequestMapping(value = "/profileList", method = RequestMethod.GET)
    public String getBigPlayList(ModelMap model) {
	model.addAttribute("profileList", profileRepository.findAll(new Sort(Direction.ASC, "lastName", "firstName", "username")));
	return "profileList";
    }

}