package serviceplaybook.mongorepo;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import serviceplaybook.model.BigPlayItem;

public interface BigPlayRepository extends PagingAndSortingRepository<BigPlayItem, String> {
	@Query("{},{_id:0,level1:1}")
	List<BigPlayItem> findLevel1Distinct();
	List<BigPlayItem> findItemsByLevel1 (String level1);
	List<BigPlayItem> findItemsByLevel1 (String level1,Sort sort);
	List<BigPlayItem> findAll(Sort sort);
}
