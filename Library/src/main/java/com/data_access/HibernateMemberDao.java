package com.data_access;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.domain.Book;
import com.domain.Entity;
import com.domain.Member;
@Repository
public class HibernateMemberDao implements MemberDao {

	private SessionFactory sessionFactory;

	@Autowired
	public HibernateMemberDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public void insert(Entity entity) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(entity);
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

	public void delete(Object uniqueValue) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(Member.class);
		 	Member x = (Member) cr.add(Restrictions.eq("email", uniqueValue)).uniqueResult();
			session.delete(x);
			tx.commit();
		} catch (RuntimeException e) {
			if (tx == null)
				tx.rollback();
			throw e;
		} finally {
			session.close();
		}

	}

	public List<Entity> listAll() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<Entity> members = new ArrayList<Entity>();
		try {
			tx = session.beginTransaction();
			String hql = "FROM Member";
			Query query = session.createQuery(hql);
			members = query.list();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			session.close();
		}

		return members;
	}

	public void update(Entity entity) {
		// the behavior of this method is currently undefined

	}

	@Override
	public Member findByEmail(String email) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(Member.class);
			Member m = (Member) cr.add(Restrictions.eq("email", email)).uniqueResult();
			tx.commit();
			return m;
		} catch (RuntimeException e) {
			if (tx == null)
				tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}


}
