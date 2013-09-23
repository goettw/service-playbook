package serviceplaybook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import serviceplaybook.model.ServicePlaybookDescription;
import serviceplaybook.mongorepo.ServicePlaybookDescriptionRepository;

@Service
public class AdminService {
	@Autowired
	ServicePlaybookDescriptionRepository servicePlaybookDescriptionRepository;

	
	public ServicePlaybookDescription getServicePlaybookDescription() {
		Iterable<ServicePlaybookDescription> it = servicePlaybookDescriptionRepository.findAll();
		if (it == null || it.iterator().hasNext() == false)
			return new ServicePlaybookDescription();
		ServicePlaybookDescription servicePlaybookDescription = it.iterator().next();
		return servicePlaybookDescription;
	}

}


