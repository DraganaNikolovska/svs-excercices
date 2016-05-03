package com.seavus.twitter.data_access;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.TreeSet;

import com.seavus.twitter.domain.Tweet;

public interface TweetDao {

	public void insertTweet(String message);
	public TreeSet<Tweet> getAllMessages() ;
}
