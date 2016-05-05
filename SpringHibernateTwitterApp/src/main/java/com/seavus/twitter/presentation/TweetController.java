package com.seavus.twitter.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.seavus.twitter.domain.Tweet;
import com.seavus.twitter.domain.TwitterUser;
import com.seavus.twitter.sequrity.CurrentUser;
import com.seavus.twitter.services.TwitterService;

@Controller
@RequestMapping("/tweets")
public class TweetController {

	@Autowired
	TwitterService service;

	@RequestMapping(method = RequestMethod.GET)
	public String getTweetsView() {
		return "tweets";
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String getAllTweetsView() {
		return "all_tweets";
	}

	@RequestMapping(method = RequestMethod.POST)
	public void registerTweet(@CurrentUser TwitterUser user, @ModelAttribute Tweet tweet) {
		service.tweetMessage(tweet.getMessage(), user.getName());
	}

	@ModelAttribute("all_tweets")
	public List<Tweet> all_tweets() {
		return service.listAllTweets();
	}

	@ModelAttribute("tweets")
	public List<Tweet> myTweets(@CurrentUser TwitterUser user) {
		return service.listUsersTweets(user);
	}

	@ModelAttribute("tweet")
	public Tweet tweet() {
		return new Tweet();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String registerLike(@PathVariable("id") Long id) {
		service.registerLike(id);
		return "redirect:/tweets";
	}
}
