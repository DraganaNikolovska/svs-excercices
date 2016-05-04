package com.seavus.twitter.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.seavus.twitter.data_access.HibernateUserDao;
import com.seavus.twitter.domain.CustomUserDetails;
import com.seavus.twitter.domain.TweeterUser;

@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {

	private final HibernateUserDao hibernateUserDao;

	@Autowired
	public UserRepositoryUserDetailsService(HibernateUserDao hibernateUserDao) {
		this.hibernateUserDao = hibernateUserDao;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		TweeterUser twitterUser = hibernateUserDao.findByUserName(username);
		if (twitterUser == null) {
			throw new UsernameNotFoundException("Could not find user " + username);
		}
		return new CustomUserDetails(twitterUser);
	}
	

}
