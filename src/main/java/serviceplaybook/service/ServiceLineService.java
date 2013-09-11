package serviceplaybook.service;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import serviceplaybook.model.ServiceLine;

@Repository
public class ServiceLineService {
	@Autowired
	private MongoTemplate mongoTemplate;

	public static final String COLLECTION_NAME = "serviceLine";

	public void addServiceLine(ServiceLine serviceLine) {
		if (!mongoTemplate.collectionExists(ServiceLine.class)) {
			mongoTemplate.createCollection(ServiceLine.class);
		}
		serviceLine.setId(UUID.randomUUID().toString());
		mongoTemplate.insert(serviceLine, COLLECTION_NAME);
	}

	public List<ServiceLine> listServiceLine() {
		return mongoTemplate.findAll(ServiceLine.class, COLLECTION_NAME);
	}

	public void deleteServiceLine(ServiceLine ServiceLine) {
		mongoTemplate.remove(ServiceLine, COLLECTION_NAME);
	}

	public void updateServiceLine(ServiceLine ServiceLine) {
		mongoTemplate.save(ServiceLine, COLLECTION_NAME);
	}

	public ServiceLine findServiceLineById(String id) {
		return mongoTemplate.findOne(query(where("_id").is(id)), ServiceLine.class);
	}

}
