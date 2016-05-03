package com.seavus.twitter.data_access;


import java.util.List;


import com.seavus.twitter.domain.Tweet;
import com.seavus.twitter.domain.TweeterUser;

public interface TweetDao {

	public void insertTweet(String message, TweeterUser user);

	public List <Tweet> findAllTweets();
}
