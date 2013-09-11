package serviceplaybook.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import serviceplaybook.model.BigPlayItem;
import serviceplaybook.mongorepo.BigPlayRepository;

@Controller
public class AdminController {
	@Autowired
	BigPlayRepository bigPlayRepository;

	@RequestMapping(value = "/admin/bigPlayItem/List", method = RequestMethod.GET)
	public String getBigPlayList(ModelMap model) {
		model.addAttribute("bigPlayList", bigPlayRepository.findAll());
		return "bigPlayList";
	}

	@RequestMapping(value = "/admin/bigPlayItem/new", method = RequestMethod.GET)
	public String getBigPlayNew(ModelMap model) {
		model.addAttribute("bigPlayItem", new BigPlayItem());
		return "bigPlayItemEdit";
	}

	@RequestMapping(value = "/admin/bigPlayItem/edit/{id}", method = RequestMethod.GET)
	public String bigPlayItemEdit(@PathVariable String id, ModelMap model) {
		BigPlayItem bigPlayItem = bigPlayRepository.findOne(id);
		model.addAttribute("bigPlayItem", bigPlayItem);
		return "bigPlayItemEdit";
	}

	@RequestMapping(value = "/admin/bigPlayItem/submit", method = RequestMethod.POST)
	public String bigPlayItemSubmit(@RequestParam String action, @ModelAttribute BigPlayItem bigPlayItem, ModelMap model) {

		if (action.equals("Save")) {
			if (bigPlayItem.getId().equals(""))
				bigPlayItem.setId(UUID.randomUUID().toString());
			bigPlayItem = bigPlayRepository.save(bigPlayItem);
		}

		return "redirect:/admin/bigPlayItem/List";

	}
	
	@RequestMapping(value = "/admin/bigPlayItem/delete/{id}", method = RequestMethod.GET)
	public String bigPlayItemDelete(@PathVariable String id, ModelMap model) {
		bigPlayRepository.delete(id);
		
		return "redirect:/admin/bigPlayItem/List";
	}
}
