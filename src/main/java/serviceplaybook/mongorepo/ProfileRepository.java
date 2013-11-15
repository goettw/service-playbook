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
	 @Query(value="{$or : [{lastName:{$regex:?0,$options:'i'}},{firstName:{$regex:?0,$options:'i'}}]}", fields="{title:1,lastName:1,firstName:1,emcFunction:1,emailAddress:1}") 
	 List<Profile> findProfilesByName (String query);
}
