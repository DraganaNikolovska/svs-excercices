package com.seavus.twitter.services;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.seavus.twitter.data_access.TweetDao;
import com.seavus.twitter.data_access.UserDao;
import com.seavus.twitter.domain.Tweet;
import com.seavus.twitter.domain.TwitterUser;
import com.seavus.twitter.presentation.TwitterConsoleController;

@Service
public class TwitterService {

	private TweetDao tweeterDao;
	private UserDao userDao;

	@Autowired
	public TwitterService(TweetDao messageDao, UserDao userDao) {
		this.tweeterDao = messageDao;
		this.userDao = userDao;
	}

	public void tweetMessage(String tweet, String username) {
		TwitterUser user = userDao.findByUserName(username);
		tweeterDao.insertTweet(tweet, user);
	}

	public List<Tweet> listAllTweets() {

		List<Tweet> list = tweeterDao.findAllTweets();
		for (Tweet message : list) {
			System.out.println(message);
		}
		return list;

	}

	public void addNewUser(String username, String password, String surname, String email) {
		userDao.insertUser(username, password, surname, email);
	}

	public Long getTotalNumberOfLikesForUser(TwitterUser tweeterUser) {
		return tweeterDao.getTotalNumberOfLikes(tweeterUser);
	}

	public List<Tweet> listUsersTweets(TwitterUser user) {
		return tweeterDao.listUsersTweets(user);
	}

	public void registerLike(Long id) {
		tweeterDao.registerLike(id);;
	}
}
