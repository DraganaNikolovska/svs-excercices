package data_access;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import domain.Book;

public class HibernateBookDao implements BookDao {

	private SessionFactory sessionFactory;

	public HibernateBookDao(SessionFactory s) {
		this.sessionFactory = s;
	}

	public void insertBook(String isbn, String title) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Book book = new Book();
			book.setIsbn(isbn);
			book.setTitle(title);
			session.save(book);
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Transaction exception");
		} finally {
			session.close();
		}

	}

	public void updateBook(String isbn, String title) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String hql = "UPDATE Book set title = :title WHERE isbn = :isbn";
			Query query = session.createQuery(hql);
			query.setParameter("title", title);
			query.setParameter("isbn", isbn);
			query.executeUpdate();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Transaction exception");
			;
		} finally {
			session.close();
		}

	}

	public void deleteBook(String isbn) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			String hql = "DELETE FROM Book where isbn = :isbn";
			Query query = session.createQuery(hql);
			query.setParameter("isbn", isbn);
			query.executeUpdate();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Transaction exception");
		} finally {
			session.close();
		}

	}

	public ArrayList<Book> listAll() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List results = new ArrayList<Book>();
		try {
			tx = session.beginTransaction();
			String hql = "FROM Book";
			Query query = session.createQuery(hql);
			results = query.list();
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null) {
				tx.rollback();
			}
			System.out.println("Transaction exception");
		} finally {
			session.close();
		}
		return (ArrayList<Book>) results;

	}

}
