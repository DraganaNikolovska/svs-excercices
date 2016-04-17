package twitter;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class TwitterApp {

	public static void main(String[] args) throws IOException, ParseException {
		Twitter twitter = new Twitter();

		Scanner consoleScanner = new Scanner(System.in);
		int userInput;
		String message;
		while ((userInput = consoleScanner.nextInt()) != 3) {
			consoleScanner.nextLine();
			switch (userInput) {
			case 1:
				message = consoleScanner.nextLine();
				twitter.tweetMessage(message);
				break;
			case 2:
				twitter.listAll();
			}
		}
	}
}
