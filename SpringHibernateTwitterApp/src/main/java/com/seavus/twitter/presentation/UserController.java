package com.seavus.twitter.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.seavus.twitter.domain.TwitterUser;
import com.seavus.twitter.services.TwitterService;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	TwitterService twitterService;

	
}
