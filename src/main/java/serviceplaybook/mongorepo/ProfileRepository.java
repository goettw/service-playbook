package serviceplaybook.mongorepo;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import serviceplaybook.model.Profile;
@Repository
public interface ProfileRepository extends PagingAndSortingRepository<Profile, String> {
	 List<Profile> findProfilesByEmailAddress (String emailAddress);
	 List<Profile> findProfilesByLastNameLike (String lastName);
	 @Query("{$or : [{lastName:{$regex:?0,$options:'i'}},{firstName:{$regex:?0,$options:'i'}}]}") 
	 List<Profile> findProfilesByName (String query);
}
