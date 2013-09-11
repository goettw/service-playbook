package serviceplaybook.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import serviceplaybook.model.BigPlayItem;
import serviceplaybook.model.ListContainer;
import serviceplaybook.model.ServiceListEntry;
import serviceplaybook.model.ServiceOffer;
import serviceplaybook.mongorepo.BigPlayRepository;
import serviceplaybook.service.ServiceOfferService;

public class ServiceNavigator {
	private ArrayList<ListContainer<ListContainer<ServiceListEntry>>> bigDataCatalog = null;
	@Autowired
	BigPlayRepository bigPlayRepository;
	@Autowired
	ServiceOfferService serviceOfferService;

	public void reloadBigPlays() {
		bigDataCatalog = new ArrayList<ListContainer<ListContainer<ServiceListEntry>>>();
		List<BigPlayItem> level1List = bigPlayRepository.findLevel1Distinct();
		HashMap<String, String> disctinctLevel1 = new HashMap<String, String>();

		for (Iterator<BigPlayItem> it = level1List.iterator(); it.hasNext();) {
			ListContainer<ListContainer<ServiceListEntry>> level1Container = new ListContainer<ListContainer<ServiceListEntry>>();
			BigPlayItem bigPlayItem = it.next();
			String level1Label = bigPlayItem.getLevel1();
			if (disctinctLevel1.containsKey(level1Label))
				continue;
			disctinctLevel1.put(level1Label, level1Label);
			level1Container.setLabel(level1Label);
			List<BigPlayItem> level2List = bigPlayRepository.findItemsByLevel1(level1Label);
			ArrayList<ListContainer<ServiceListEntry>> level2ContainerList = new ArrayList<ListContainer<ServiceListEntry>>();
			for (Iterator<BigPlayItem> it2 = level2List.iterator(); it2.hasNext();) {
				ListContainer<ServiceListEntry> level2Container = new ListContainer<ServiceListEntry>();
				BigPlayItem bigPlayItem2 = it2.next();
				String level2Label = bigPlayItem2.getLevel2();
				level2Container.setLabel(level2Label);
				List<ServiceOffer> serviceOfferList = serviceOfferService.findServiceOfferByPlay(bigPlayItem2.getId());
				ArrayList<ServiceListEntry> serviceListEntryList = new ArrayList<ServiceListEntry>();
				for (Iterator<ServiceOffer> it3 = serviceOfferList.iterator(); it3.hasNext();) {
					ServiceOffer serviceOffer = it3.next();
					ServiceListEntry serviceListEntry = new ServiceListEntry();
					serviceListEntry.setId(serviceOffer.getId());
					serviceListEntry.setLabel(serviceOffer.getLabel());
					serviceListEntryList.add(serviceListEntry);
				}
				level2Container.setEntries(serviceListEntryList);
				level2ContainerList.add(level2Container);
			}
			level1Container.setEntries(level2ContainerList);
			bigDataCatalog.add(level1Container);
		}
	}

	public ArrayList<ListContainer<ListContainer<ServiceListEntry>>> getBigDataCatalog() {
		//if (bigDataCatalog == null)
			reloadBigPlays();
		return bigDataCatalog;
	}
}
