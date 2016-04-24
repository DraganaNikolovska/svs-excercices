package data_access;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import domain.Entity;
import domain.Magazine;

public class HibernateMagazineDao implements MagazineDao {

	private SessionFactory sessionFactory;

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

	public void update(Entity entity) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String hql = "UPDATE Magazine set title = :title WHERE issn = :issn";
			Magazine m = (Magazine) entity;
			Query query = session.createQuery(hql);
			query.setParameter("title", m.getTitle());
			query.setParameter("issn", m.getIssn());
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

	public void delete(Object uniqueValue) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String hql = "DELETE FROM Magazine M WHERE M.issn = :issn";
			Query query = session.createQuery(hql);
			query.setParameter("isbn", uniqueValue);
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

	public List<Entity> listAll() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<Entity> magazines = new ArrayList<Entity>();
		try {
			tx = session.beginTransaction();
			String hql = "FROM Magazine";
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


}
