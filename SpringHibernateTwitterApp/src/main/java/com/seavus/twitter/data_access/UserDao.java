package com.seavus.twitter.data_access;

import java.util.List;

import com.seavus.twitter.domain.TwitterUser;
public interface UserDao {

	public void insertUser(String userName, String password, String surname, String email);

	public List<TwitterUser> findAll();
	
	public TwitterUser findByUserName(String username);
}
