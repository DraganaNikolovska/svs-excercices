package com.seavus.twitter.data_access;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import com.seavus.twitter.domain.Tweet;

public class FileTweetDao implements TweetDao {

	private SimpleDateFormat sdf;

	public FileTweetDao()  {

		sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

	}

	@Override
	public void insertTweet(String message) {

		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter("messages.txt", true);
			String sDate = sdf.format(new Date());
			fileWriter.write(sDate + " - " + message);
			fileWriter.write(System.getProperty("line.separator"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				fileWriter.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

	}

	@Override
	public TreeSet<Tweet> getAllMessages() {
		BufferedReader reader = null;
		// treeset is used for sorting the messages in chronological order
		TreeSet<Tweet> messages = new TreeSet<>();

		try {
			reader = new BufferedReader(new FileReader("messages.txt"));
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(" - ");
				Date date = sdf.parse(parts[0]);
				Tweet message = new Tweet(parts[1], new java.sql.Timestamp(date.getTime()));
				messages.add(message);
			}
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

		return messages;

	}

}
