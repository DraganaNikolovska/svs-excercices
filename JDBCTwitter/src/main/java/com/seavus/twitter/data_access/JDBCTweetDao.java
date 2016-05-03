package com.seavus.twitter.data_access;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import com.seavus.twitter.domain.Tweet;

public class JDBCTweetDao implements TweetDao {

	@Override
	public void insertTweet(String message) {
		Connection connection = null;
		try {
			connection = JDBCDriverManager.getMyConnection();
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into tweet (message, date) values (?, ?)");
			preparedStatement.setString(1, message);
			preparedStatement.setTimestamp(2, new java.sql.Timestamp(new Date().getTime()));
			preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

	}

	@Override
	public TreeSet<Tweet> getAllMessages() {

		Connection connection = null;

		try {
			connection = JDBCDriverManager.getMyConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select * from tweet");
			ResultSet set = preparedStatement.executeQuery();
			TreeSet<Tweet> tweets = new TreeSet<>();
			while (set.next()) {
				String message = set.getString("message");
				Timestamp date = set.getTimestamp("date");
				Tweet tweet = new Tweet(message, date);
				tweets.add(tweet);

			}
			return tweets;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

}
