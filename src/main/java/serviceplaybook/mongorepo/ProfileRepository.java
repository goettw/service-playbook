package serviceplaybook.mongorepo;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import serviceplaybook.model.Profile;
@Repository
public interface ProfileRepository extends PagingAndSortingRepository<Profile, String> {
	 List<Profile> findProfilesByEmailAddress (String emailAddress);

}
