package com.seavus.twitter.domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.sql.Timestamp;;
@Entity
@Table(name = "tweet")
public class Tweet implements Comparable<Tweet> {

	@Id @GeneratedValue private Long id;
	@ManyToOne
	private TweeterUser tweeterUser;
	private String message;
	private Timestamp date;
	private Integer likes;

	public Tweet() {
		
	}

	public Tweet(String message, Timestamp date) {
		this.likes = 0;
		this.message = message;
		this.date = date;
	}

	public int compareTo(Tweet o) {
		return o.date.compareTo(date);
	}

	@Override
	public String toString() {
		return date + " - " + message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}
	public void setTweeterUser(TweeterUser tweeterUser) {
		this.tweeterUser = tweeterUser;
	}
	public TweeterUser getTweeterUser() {
		return tweeterUser;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

}
