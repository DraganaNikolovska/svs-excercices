package data_access;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import domain.Book;
import domain.Entity;
import domain.Member;
import domain.Publication;

public class HibernateBookDao implements Dao {

	private SessionFactory sessionFactory;

	public HibernateBookDao(SessionFactory s) {
		this.sessionFactory = s;
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
			String hql = "UPDATE Book set title = :title WHERE isbn = :isbn";
			Book b = (Book) entity;
			Query query = session.createQuery(hql);
			query.setParameter("title", b.getTitle());
			query.setParameter("isbn", b.getIsbn());
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
			String hql = "DELETE FROM Book B WHERE B.isbn = :isbn";
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
		List<Entity> books = new ArrayList<Entity>();
		try {
			tx = session.beginTransaction();
			String hql = "FROM Book";
			Query query = session.createQuery(hql);
			books = query.list();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			session.close();
		}

		return books;
	}

	public Entity get(Object uniqueValue) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(Book.class);
			Book b = (Book) cr.add(Restrictions.eq("isbn", uniqueValue)).uniqueResult();
			tx.commit();
			return b;
		} catch (RuntimeException e) {
			if (tx == null)
				tx.rollback();
			throw e;
		} finally {
			session.close();
		}
	}

}
