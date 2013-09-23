package serviceplaybook.mongorepo;

import org.springframework.data.repository.CrudRepository;

import serviceplaybook.model.ServicePlaybookDescription;

public interface ServicePlaybookDescriptionRepository extends CrudRepository <ServicePlaybookDescription, String> {
}
