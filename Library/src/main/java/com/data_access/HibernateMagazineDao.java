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
import com.domain.Magazine;

@Repository
public class HibernateMagazineDao implements MagazineDao {

	private SessionFactory sessionFactory;

	@Autowired
	public HibernateMagazineDao(SessionFactory sessionFactory) {
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
		List<Entity> magazines = new ArrayList<Entity>();
		try {
			tx = session.beginTransaction();
			String hql = "FROM Magazine m ORDER BY m.id";
			Query query = session.createQuery(hql);
			magazines = query.list();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			session.close();
		}

		return magazines;
	}

	@Override
	public Magazine fineByIssn(String issn) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(Magazine.class);
			Magazine m = (Magazine) cr.add(Restrictions.eq("issn", issn)).uniqueResult();
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

	@Override
	public void updateMagazineTitle(String issn, String title) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String hql = "UPDATE Magazine set title = :title WHERE issn = :issn";
			Query query = session.createQuery(hql);
			query.setParameter("title", title);
			query.setParameter("issn", issn);
			query.executeUpdate();
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
	public void delete(Integer id) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String hql = "DELETE FROM Magazine M WHERE M.id = :id";
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
	public Entity findById(Integer id) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			System.out.println("id = " + id);
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(Magazine.class);
			Magazine m = (Magazine) cr.add(Restrictions.eq("id", id)).uniqueResult();
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
