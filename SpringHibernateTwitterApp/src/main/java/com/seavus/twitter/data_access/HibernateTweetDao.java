package com.seavus.twitter.data_access;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.seavus.twitter.domain.Tweet;
import com.seavus.twitter.domain.TweeterUser;
@Repository
public class HibernateTweetDao implements TweetDao {

	private SessionFactory sessionFactory;

	@Autowired
	public HibernateTweetDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void insertTweet(String message, TweeterUser user) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Tweet tweet = new Tweet();
			tweet.setMessage(message);
			tweet.setDate(new java.sql.Timestamp(new Date().getTime()));
			tweet.setTweeterUser(user);
			session.save(tweet);
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			session.close();
		}

	}

	@Override
	public List<Tweet> findAllTweets() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<Tweet> tweets = new ArrayList<Tweet>();
		try {
			tx = session.beginTransaction();
			String hql = "FROM Tweet t ORDER BY t.date DESC";
			Query query = session.createQuery(hql);
			tweets = query.list();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			session.close();
		}

		return tweets;
	}

	
}
