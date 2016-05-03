package com.seavus.twitter.data_access;

import java.util.List;

import com.seavus.twitter.domain.TweeterUser;
public interface UserDao {

	public void insertUser(String userName, String password);

	public List<TweeterUser> findAll();
	
	public TweeterUser findByUserName(String username);
}
