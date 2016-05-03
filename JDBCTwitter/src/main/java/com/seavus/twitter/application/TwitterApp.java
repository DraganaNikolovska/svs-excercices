package com.seavus.twitter.application;

import java.util.Scanner;
import com.seavus.twitter.data_access.FileTweetDao;
import com.seavus.twitter.data_access.JDBCTweetDao;
import com.seavus.twitter.presentation.TwitterController;
import com.seavus.twitter.services.TwitterService;

public class TwitterApp {

	public enum DATABASE_IMPLEMENTATION {
		FILE, JDBC
	}

	public static TwitterController twitterController;

	public static void main(String[] args) {
		chooseImplementation();
		twitterController.run();
	}

	public static void chooseImplementation() {
		System.out.println("Enter 1 for FILE implementation");
		System.out.println("Enter 2 for JDBC implementation");
		Scanner scanner = new Scanner(System.in);
		int input = scanner.nextInt();
		if (input == 1) {
			fileImplementation();
		} else {
			jdbcImplementation();
		}

	}

	private static void jdbcImplementation() {
		twitterController = new TwitterController(new TwitterService(new JDBCTweetDao()));
		
	}

	public static void fileImplementation() {
		twitterController = new TwitterController(new TwitterService(new FileTweetDao()));

	}
}
