package serviceplaybook.service;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import serviceplaybook.model.ServiceOffer;
@Repository
public class ServiceOfferService {
	
	@Autowired
	private MongoTemplate mongoTemplate;

	private String COLLECTION_NAME = "serviceOffer";
	
	public String getCOLLECTION_NAME() {
		return COLLECTION_NAME;
	}

	public void addServiceOffer(ServiceOffer serviceOffer) {
		if (!mongoTemplate.collectionExists(ServiceOffer.class)) {
			mongoTemplate.createCollection(ServiceOffer.class);
		}		
		serviceOffer.setId(UUID.randomUUID().toString());
		mongoTemplate.insert(serviceOffer, getCollectionName());
	}
	
	public List<ServiceOffer> listServiceOffer() {
		return listServiceOffer("label", Direction.ASC);

	}
	
	public List<ServiceOffer> listServiceOffer(String sortLabel, Direction sort) {
		Query query = new Query();
		query.with(new Sort(sort, sortLabel));
		return mongoTemplate.find(query, ServiceOffer.class);
	}
	
	public void deleteServiceOffer(ServiceOffer serviceOffer) {
		mongoTemplate.remove(serviceOffer, getCollectionName());
	}
	
	
	public void updateServiceOffer(ServiceOffer serviceOffer) {
		mongoTemplate.save(serviceOffer, getCollectionName());		
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
		query.with(new Sort(Direction.ASC,"label"));
		return mongoTemplate.find(query ,ServiceOffer.class);
	}

	public String getCollectionName() {
		return COLLECTION_NAME;
	}
	
}
