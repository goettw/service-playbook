package serviceplaybook.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import serviceplaybook.model.BigPlayItem;
import serviceplaybook.model.ServiceOffer;
import serviceplaybook.mongorepo.BigPlayRepository;
import serviceplaybook.service.AdminService;
import serviceplaybook.service.ServiceOfferService;

@Controller
public class BigPlayController {
	@Autowired
	BigPlayRepository bigPlayRepository;
	@Autowired
	private ServiceOfferService serviceOfferService;


	@RequestMapping(value = "/admin/bigPlayItemList", method = RequestMethod.GET)
	public String getBigPlayList(ModelMap model) {
		model.addAttribute("bigPlayList", bigPlayRepository.findAll(new Sort(Direction.ASC,"sortOrderNo","level1","level2")));
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
	
	@RequestMapping(value = "/bigPlayItem/{id}", method = RequestMethod.GET)
	public String bigPlayItemView(@PathVariable String id, ModelMap model) {
		BigPlayItem bigPlayItem = bigPlayRepository.findOne(id);
		model.addAttribute("bigPlayItem", bigPlayItem);
		List<ServiceOffer> serviceOfferList = serviceOfferService.findServiceOfferByPlay(bigPlayItem.getId());
		model.addAttribute("serviceOfferList", serviceOfferList);
		
		model.addAttribute("editUrl","/admin/bigPlayItem/edit/"+id);
		return "bigPlayItem";
	}

	@RequestMapping(value = "/admin/bigPlayItem/submit", method = RequestMethod.POST)
	public String bigPlayItemSubmit(@RequestParam String action, @ModelAttribute BigPlayItem bigPlayItem, ModelMap model) {
		if (action.equals("Save")) {
			if (bigPlayItem.getId().equals(""))
				bigPlayItem.setId(UUID.randomUUID().toString());
			bigPlayItem = bigPlayRepository.save(bigPlayItem);
		}
		model.put("editUrl","/admin/bigPlayItem/edit/"+bigPlayItem.getId());
		return "redirect:/bigPlayItem/"+bigPlayItem.getId();
	}
	
	@RequestMapping(value = "/admin/bigPlayItem/delete/{id}", method = RequestMethod.GET)
	public String bigPlayItemDelete(@PathVariable String id, ModelMap model) {
		bigPlayRepository.delete(id);		
		return "redirect:/admin/bigPlayItemList";
	}
}
