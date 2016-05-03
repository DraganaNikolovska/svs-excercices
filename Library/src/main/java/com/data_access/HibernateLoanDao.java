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

import com.domain.Entity;
import com.domain.Loan;
import com.domain.Member;
import com.domain.Publication;

@Repository
public class HibernateLoanDao implements LoanDao {
	private SessionFactory sessionFactory;

	@Autowired
	public HibernateLoanDao(SessionFactory sessionFactory) {
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

	public List<Entity> listAll() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<Entity> loans = new ArrayList<Entity>();
		try {
			tx = session.beginTransaction();
			String hql = "FROM Loan l ORDER BY l.id";
			Query query = session.createQuery(hql);
			loans = query.list();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			session.close();
		}

		return loans;
	}

	@Override
	public Loan findById(Integer id) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(Loan.class);
			Loan l = (Loan) cr.add(Restrictions.eq("id", id)).uniqueResult();
			tx.commit();
			return l;
		} catch (RuntimeException e) {
			if (tx == null)
				tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

	@Override
	public void delete(Integer id) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String hql = "DELETE FROM Loan l WHERE l.id = :id";
			Query query = session.createQuery(hql);
			query.setParameter("id", id);
			query.executeUpdate();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			session.close();
		}

	}

	@Override
	public void unregisterLoan(Publication publication) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String hql = "DELETE FROM Loan l WHERE l.publication = :publication";
			Query query = session.createQuery(hql);
			query.setParameter("publication", publication);
			query.executeUpdate();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			session.close();
		}

	}
}
