package com.seavus.twitter.data_access;

import java.util.List;

import com.seavus.twitter.domain.Tweet;
import com.seavus.twitter.domain.TwitterUser;

public interface TweetDao {

	public void insertTweet(String message, TwitterUser user);

	public List<Tweet> findAllTweets();

	public Long getTotalNumberOfLikes(TwitterUser tweeterUser);

	public List<Tweet> listUsersTweets(TwitterUser user);

	public void registerLike(Long tweetId);
}
