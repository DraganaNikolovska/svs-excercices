package com.seavus.twitter.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tweeter_user")
public class TwitterUser implements Serializable {

	@OneToMany(mappedBy = "tweeterUser")
	private Set<Tweet> tweets;

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String password;
	private String surname;
	private String email;

	public TwitterUser() {

	}

	public TwitterUser(TwitterUser user) {
		this.name = user.getName();
		this.password = user.getPassword();
		this.id = user.getId();
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Tweet> getTweets() {
		return tweets;
	}

	public void setTweets(Set<Tweet> tweets) {
		this.tweets = tweets;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private static final long serialVersionUID = 2738859149330833739L;
}
