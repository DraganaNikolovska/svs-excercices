package com.seavus.twitter.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;;

public class Tweet implements Comparable<Tweet> {

	private String message;
	private Timestamp date;

	public Tweet(String message, Timestamp date) {
		this.message = message;
		this.date = date;
	}

	@Override
	public int compareTo(Tweet o) {
		return o.date.compareTo(date); 
	}

	@Override
	public String toString() {
		return date + " - " + message;
	}

}
