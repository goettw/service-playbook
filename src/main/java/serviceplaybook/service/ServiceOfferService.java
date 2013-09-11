package serviceplaybook.service;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import serviceplaybook.model.ServiceOffer;
@Repository
public class ServiceOfferService {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	public static final String COLLECTION_NAME = "serviceOffer";
	
	public void addServiceOffer(ServiceOffer serviceOffer) {
		if (!mongoTemplate.collectionExists(ServiceOffer.class)) {
			mongoTemplate.createCollection(ServiceOffer.class);
		}		
		serviceOffer.setId(UUID.randomUUID().toString());
		mongoTemplate.insert(serviceOffer, COLLECTION_NAME);
	}
	
	public List<ServiceOffer> listServiceOffer() {
		return mongoTemplate.findAll(ServiceOffer.class, COLLECTION_NAME);
	}
	
	public void deleteServiceOffer(ServiceOffer serviceOffer) {
		mongoTemplate.remove(serviceOffer, COLLECTION_NAME);
	}
	
	
	public void updateServiceOffer(ServiceOffer serviceOffer) {
		mongoTemplate.save(serviceOffer, COLLECTION_NAME);		
	}
	
	public ServiceOffer findServiceOfferById (String id) {
		return mongoTemplate.findOne(query(where("_id").is(id)),ServiceOffer.class);
	}
	
	public List<ServiceOffer> findServiceOfferByCategory(String id) {
		Query query = new Query() ;
		query.addCriteria(new Criteria().andOperator(Criteria.where("serviceCategory").is(id),Criteria.where("status").is("released")));
		return mongoTemplate.find(query ,ServiceOffer.class);
	}
	
	public List<ServiceOffer> findServiceOfferByPlay(String id) {
		Query query = new Query() ;
		query.addCriteria(new Criteria().andOperator(Criteria.where("bigPlay").in(id),Criteria.where("status").is("released")));
		return mongoTemplate.find(query ,ServiceOffer.class);
	}
	
}
