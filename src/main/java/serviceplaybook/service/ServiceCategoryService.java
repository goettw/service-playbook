package serviceplaybook.service;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import serviceplaybook.model.ServiceCategory;

@Repository
public class ServiceCategoryService {
	@Autowired
	private MongoTemplate mongoTemplate;

	public static final String COLLECTION_NAME = "serviceCategory";
	public void addServiceCategory(ServiceCategory serviceCategory) {
		if (!mongoTemplate.collectionExists(ServiceCategory.class)) {
			mongoTemplate.createCollection(ServiceCategory.class);
		}		
		serviceCategory.setId(UUID.randomUUID().toString());
		mongoTemplate.insert(serviceCategory, COLLECTION_NAME);
	}
	
	public List<ServiceCategory> listServiceCategory() {
		return mongoTemplate.findAll(ServiceCategory.class, COLLECTION_NAME);
	}
	
	public void deleteServiceCategory(ServiceCategory serviceCategory) {
		mongoTemplate.remove(serviceCategory, COLLECTION_NAME);
	}
	
	public void updateServiceCategory(ServiceCategory ServiceCategory) {
		mongoTemplate.save(ServiceCategory, COLLECTION_NAME);		
	}
	
	public ServiceCategory findServiceCategoryById (String id) {
		return mongoTemplate.findOne(query(where("_id").is(id)),ServiceCategory.class);
	}
	
	
	public List<ServiceCategory>  findServiceCategoryByServiceLine (String id) {
		return mongoTemplate.find(query(where("serviceLine").is(id)),ServiceCategory.class);
	}
	
}
