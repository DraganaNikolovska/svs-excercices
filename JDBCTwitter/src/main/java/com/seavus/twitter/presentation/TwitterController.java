package com.seavus.twitter.presentation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

import com.seavus.twitter.data_access.TweetDao;
import com.seavus.twitter.data_access.FileTweetDao;
import com.seavus.twitter.domain.Tweet;
import com.seavus.twitter.services.TwitterService;

public class TwitterController {

	private final String TWEET = "1";
	private final String LIST_TWEETS = "2";
	private final String QUIT = "3";
	private TwitterService twitterService;
	private Scanner scanner;
	
	public TwitterController(TwitterService service){
		this.twitterService = service;
		scanner = new Scanner(System.in);
	}	
	public void run()  {
		
		String line = "", message;
		showMenuOptions();
		while (!(line = scanner.nextLine()).equals(QUIT)) {
			switch (line) {
			case TWEET:
				System.out.println("Write new tweet");
				message = scanner.nextLine();
				twitterService.tweetMessage(message);
				break;
			case LIST_TWEETS:
				twitterService.listAll();
				break;
			}
			showMenuOptions();
		}

	}

	private void showMenuOptions() {
		System.out.println("1. Tweet new message");
		System.out.println("2. List all messages");
		System.out.println("3. Quit the app");
	}
}
