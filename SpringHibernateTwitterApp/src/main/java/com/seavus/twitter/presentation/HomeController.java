package com.seavus.twitter.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.seavus.twitter.domain.Tweet;
import com.seavus.twitter.domain.TwitterUser;
import com.seavus.twitter.sequrity.CurrentUser;
import com.seavus.twitter.services.TwitterService;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private TwitterService twitterService;

	@RequestMapping(method = RequestMethod.GET)
	public String getView() {
		return "home";
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String getView2() {
		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginView() {
		return "login";
	}

	@ModelAttribute("user")
	public TwitterUser user(@CurrentUser TwitterUser user) {
		return user;
	}

	@ModelAttribute("tweet")
	public Tweet tweet() {
		return new Tweet();
	}

	@ModelAttribute("likes")
	public Long likes(@CurrentUser TwitterUser user) {
		return twitterService.getTotalNumberOfLikesForUser(user);
	}
}
