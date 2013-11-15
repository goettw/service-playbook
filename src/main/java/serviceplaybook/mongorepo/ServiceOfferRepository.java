package serviceplaybook.mongorepo;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import serviceplaybook.model.ServiceOffer;
@Repository
public interface ServiceOfferRepository extends PagingAndSortingRepository<ServiceOffer, String>{

    @Query("{'emcContacts.username':?0}")
    public List<ServiceOffer> findServiceOffersByContactId(String contactId, Sort sort);
}
