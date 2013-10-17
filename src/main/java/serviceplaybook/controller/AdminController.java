package serviceplaybook.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import serviceplaybook.model.GrantedAuthorityContainer;
import serviceplaybook.model.Profile;
import serviceplaybook.model.ServicePlaybookDescription;
import serviceplaybook.mongorepo.ProfileRepository;
import serviceplaybook.mongorepo.ServicePlaybookDescriptionRepository;

@Controller
public class AdminController {
	@Autowired
	ServicePlaybookDescriptionRepository servicePlaybookDescriptionRepository;
	@Autowired
	ProfileRepository profileRepository;
	@Autowired
	org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder bCryptPasswordEncoder;

	private List<String> authorityList;// = new ArrayList<String>();

	public List<String> getAuthorityList() {
		return authorityList;
	}

	@Autowired
	public void setAuthorityList(
			@Value("#{authorities}") List<String> authorityList) {
		this.authorityList = authorityList;
	}

	@RequestMapping(value = "/admin/servicePlaybookDescription", method = RequestMethod.GET)
	public String getServicePlaybookDescription(ModelMap model) {
		model.put("servicePlaybookDescription", getServicePlaybookDescription());
		model.put("editUrl", "/admin/servicePlaybookDescription/edit");
		return "servicePlaybookDescription";
	}

	@RequestMapping(value = "/admin/servicePlaybookDescription/edit", method = RequestMethod.GET)
	public String editServicePlaybookDescription(ModelMap model) {
		model.put("servicePlaybookDescription", getServicePlaybookDescription());
		return "servicePlaybookDescriptionEdit";
	}

	@RequestMapping(value = "/admin/servicePlaybookDescription/submit", method = RequestMethod.POST)
	public String saveServicePlaybookDescription(
			@RequestParam String action,
			@ModelAttribute ServicePlaybookDescription servicePlaybookDescription) {
		servicePlaybookDescriptionRepository.save(servicePlaybookDescription);
		return "redirect:/admin/servicePlaybookDescription";
	}

	/*
	 * CRUD methods for profile administration
	 */
	@RequestMapping(value = "/admin/profile/new", method = RequestMethod.GET)
	public String profileNew(ModelMap model) {

		model.addAttribute("profile", new Profile());
		model.addAttribute("authorityList", authorityList);
		return "profileEdit";
	}

	@RequestMapping(value = "/admin/profile/edit/{id}", method = RequestMethod.GET)
	public String profileEdit(@PathVariable String id, ModelMap model) {
		Profile profile = profileRepository.findOne(id);
		model.addAttribute("profile", profile);
		model.addAttribute("authorityList", authorityList);
		return "profileEdit";
	}

	@RequestMapping(value = "admin/profile/submit", method = RequestMethod.POST)
	public String profileSubmit(@RequestParam String action,
			@ModelAttribute Profile profile, ModelMap model,
			BindingResult result) {
		System.out.println("profile" + profile);

		for (Iterator<GrantedAuthorityContainer> it = profile.getAuthorities()
				.iterator(); it.hasNext();) {
			GrantedAuthorityContainer grantedAuthorityContainer = it.next();
			System.out.println("1:" + grantedAuthorityContainer.getAuthority());
		}

		if (action.equals("Save")) {
			// if password has changed, we have to encrypt it
			Profile oldProfile = profileRepository.findOne(profile
					.getUsername());
			boolean encryptPassword = true;

			if (oldProfile != null && oldProfile.getPassword() != null) {
				if (profile.getPassword() == null || profile.getPassword().isEmpty())
					profile.setPassword(oldProfile.getPassword());
				if (oldProfile.getPassword().equals(profile.getPassword()))
					encryptPassword = false;
			}

			ValidationUtils.rejectIfEmptyOrWhitespace(result, "password",
					"field.required");
			 if (result.hasErrors()) {
			      return "profileEdit";
			    }

			if (encryptPassword) {
				bCryptPasswordEncoder = new BCryptPasswordEncoder();
				profile.setPassword(bCryptPasswordEncoder.encode(profile
						.getPassword()));
			}

			profile = profileRepository.save(profile);
		}
		model.put("editUrl", "/admin/profile/edit/" + profile.getUsername());
		return "redirect:/profile/" + profile.getUsername();
	}

	@RequestMapping(value = "/profile/{username}", method = RequestMethod.GET)
	public String profileView(@PathVariable String username, ModelMap model) {
		Profile profile = profileRepository.findOne(username);
		model.addAttribute("profile", profile);

		model.addAttribute("editUrl", "/admin/profile/edit/" + username);
		return "profile";
	}

	@RequestMapping(value = "/admin/profileList", method = RequestMethod.GET)
	public String getBigPlayList(ModelMap model) {
		model.addAttribute("profileList",
				profileRepository.findAll(new Sort(Direction.ASC, "username")));
		return "profileList";
	}

	/*
	 * END CRUD methods for profile administration
	 */

	private ServicePlaybookDescription getServicePlaybookDescription() {
		Iterable<ServicePlaybookDescription> it = servicePlaybookDescriptionRepository
				.findAll();
		if (it == null || it.iterator().hasNext() == false)
			return new ServicePlaybookDescription();
		ServicePlaybookDescription servicePlaybookDescription = it.iterator()
				.next();
		return servicePlaybookDescription;
	}

}
