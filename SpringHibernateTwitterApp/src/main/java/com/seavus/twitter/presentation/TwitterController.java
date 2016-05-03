package com.seavus.twitter.presentation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.seavus.twitter.data_access.TweetDao;
import com.seavus.twitter.domain.Tweet;
import com.seavus.twitter.services.TwitterService;

@Controller
public class TwitterController {

	private final String TWEET = "1";
	private final String LIST_TWEETS = "2";
	private final String NEW_USER = "3";
	private final String QUIT = "4";

	private TwitterService twitterService;
	private Scanner scanner;

	@Autowired
	public TwitterController(TwitterService service) {
		this.twitterService = service;
		scanner = new Scanner(System.in);
	}

	public void run() {

		String line = "", message, username, password;
		showMenuOptions();
		while (!(line = scanner.nextLine()).equals(QUIT)) {
			switch (line) {
			case TWEET:
				System.out.println("Write new tweet");
				message = scanner.nextLine();
				System.out.println("Enter your user name");
				username = scanner.nextLine();
				twitterService.tweetMessage(message, username);
				break;
			case LIST_TWEETS:
				twitterService.listAll();
				break;
			case NEW_USER:
				System.out.println("Enter username");
				username = scanner.nextLine();
				System.out.println("Enter password");
				password = scanner.nextLine();
				twitterService.addNewUser(username, password);
			}
			showMenuOptions();
		}

	}

	private void showMenuOptions() {
		System.out.println("1. Tweet new message");
		System.out.println("2. List all messages");
		System.out.println("3. Insert new user");
		System.out.println("4. Quit the app");
	}
}
