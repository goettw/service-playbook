package serviceplaybook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import serviceplaybook.model.Profile;
import serviceplaybook.mongorepo.ProfileRepository;

@Service
public class ProfileService implements UserDetailsService {
	
	@Autowired
	private ProfileRepository profileRepository;


 
	public UserDetails loadUserByUsername(String arg0)
			throws UsernameNotFoundException {
		System.out.println("arg01=" + arg0);
		Profile profile = profileRepository.findOne(arg0);
		if (profile == null)
			throw new UsernameNotFoundException("Username not found");
		return profile;
	}

}
