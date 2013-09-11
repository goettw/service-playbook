package serviceplaybook.mongorepo;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import serviceplaybook.model.BigPlayItem;

public interface BigPlayRepository extends CrudRepository<BigPlayItem, String> {
	@Query("{},{_id:0,level1:1}")
	List<BigPlayItem> findLevel1Distinct();
	List<BigPlayItem> findItemsByLevel1 (String level1);
}
