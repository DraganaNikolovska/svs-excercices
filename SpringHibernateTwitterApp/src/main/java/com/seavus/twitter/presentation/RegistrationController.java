package com.seavus.twitter.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.seavus.twitter.data_access.HibernateUserDao;
import com.seavus.twitter.domain.TwitterUser;
import com.seavus.twitter.services.TwitterService;

@Controller
@RequestMapping("/register")
public class RegistrationController {

	@Autowired
	TwitterService twitterService;

	@RequestMapping(method = RequestMethod.GET)
	public String getRegistrationView() {
		return "register";
	}

	@ModelAttribute("twitter_user")
	public TwitterUser setMemberAttribute() {
		return new TwitterUser();
	}

	@RequestMapping(method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("twitterUser") TwitterUser twitterUser) {
		twitterService.addNewUser(twitterUser.getName(), twitterUser.getPassword(), twitterUser.getSurname(),
				twitterUser.getEmail());
		return "redirect:/login";
	}
}
