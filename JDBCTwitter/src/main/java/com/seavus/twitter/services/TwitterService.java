package com.seavus.twitter.services;

import java.io.IOException;
import java.text.ParseException;
import java.util.TreeSet;

import com.seavus.twitter.data_access.TweetDao;
import com.seavus.twitter.data_access.FileTweetDao;
import com.seavus.twitter.domain.Tweet;
import com.seavus.twitter.presentation.TwitterController;

public class TwitterService {

	
	private TweetDao tweeterDao;

	public TwitterService(TweetDao messageDao)  {
		this.tweeterDao = messageDao;
	}

	
	public void tweetMessage(String message) {
		tweeterDao.insertTweet(message);
	}

	public void listAll()  {
		
		for (Tweet message : tweeterDao.getAllMessages()) {
			System.out.println(message);
		}

	}

}
