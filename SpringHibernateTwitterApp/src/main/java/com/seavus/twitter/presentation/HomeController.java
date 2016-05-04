package com.seavus.twitter.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.seavus.twitter.domain.Tweet;
import com.seavus.twitter.services.TwitterService;

@Controller
public class HomeController {

	@Autowired
	private TwitterService twitterService;
	
	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	@ModelAttribute("tweets")
	public List<Tweet> listAll(){
		return twitterService.listAllTweets();
	}
}
