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
import com.seavus.twitter.domain.TwitterUser;

@Repository
public class HibernateTweetDao implements TweetDao {

	private SessionFactory sessionFactory;

	@Autowired
	public HibernateTweetDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void insertTweet(String message, TwitterUser user) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Tweet tweet = new Tweet();
			tweet.setLikes(0);
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

	public Long getTotalNumberOfLikes(TwitterUser tweeterUser) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String hql = "SELECT SUM(likes) FROM Tweet WHERE tweeterUser = :user ";
			Query query = session.createQuery(hql);
			query.setParameter("user", tweeterUser);
			List<Long> list = query.list();
			tx.commit();
			return list.get(0);
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			session.close();
		}

	}

	@Override
	public List<Tweet> listUsersTweets(TwitterUser user) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<Tweet> tweets = new ArrayList<Tweet>();
		try {
			tx = session.beginTransaction();
			String hql = "FROM Tweet WHERE tweeterUser = :user";
			Query query = session.createQuery(hql);
			query.setParameter("user", user);
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

	public void registerLike(Long tweetId) {
		System.out.println(tweetId + " ----------------");
		/*Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			String hql1 = "SELECT likes FROM Tweet WHERE id = :id";
			Query query1 = session.createQuery(hql1);
			query1.setParameter("id", tweetId);
			List result = query1.list();
			if (result != null) {
				Integer currentLikes = (Integer) result.get(0);
				String hql2 = "UPDATE Tweet set likes = :likes WHERE id = :id";
				Query query2 = session.createQuery(hql2);
				query2.setParameter("likes", currentLikes + 1);
				query2.setParameter("id", tweetId);
				int n = query2.executeUpdate();
				System.out.println("****** " + n);
			}

		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			session.close();
		}*/

	}

}
