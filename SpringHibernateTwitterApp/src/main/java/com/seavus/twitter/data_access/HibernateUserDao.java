package com.seavus.twitter.data_access;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.seavus.twitter.domain.Tweet;
import com.seavus.twitter.domain.TweeterUser;
@Repository
public class HibernateUserDao implements UserDao {
	private SessionFactory sessionFactory;

	@Autowired
	public HibernateUserDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void insertUser(String userName, String password) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			TweeterUser user = new TweeterUser();
			user.setName(userName);
			user.setPassword(password);
			session.save(user);
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
	public List<TweeterUser> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TweeterUser findByUserName(String username) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(TweeterUser.class);
			TweeterUser user = (TweeterUser) cr.add(Restrictions.eq("name", username)).uniqueResult();
			tx.commit();
			return user;
		} catch (RuntimeException e) {
			if (tx == null)
				tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}
}
