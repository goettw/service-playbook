package serviceplaybook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import serviceplaybook.model.Profile;

@Controller
public class SecurityController {
	@RequestMapping(value = "/loginForm", method = RequestMethod.GET)
	public String login(ModelMap model) {
		model.addAttribute("profile",new LoginFormModel());
		return "loginForm";
	}
}
