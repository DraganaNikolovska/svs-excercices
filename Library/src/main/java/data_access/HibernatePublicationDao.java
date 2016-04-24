package data_access;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import domain.Book;
import domain.Entity;
import domain.Publication;

public class HibernatePublicationDao implements PublicationDao {

	private SessionFactory sessionFactory;

	public HibernatePublicationDao(SessionFactory s) {
		this.sessionFactory = s;
	}

	@Override
	public List<Publication> listAll() {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		List<Publication> publications = new ArrayList<Publication>();
		try {
			tx = session.beginTransaction();
			String hql = "FROM Publication";
			Query query = session.createQuery(hql);		
			publications = query.list();		
			tx.commit();
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {
			session.close();
		}

		return publications;
	}
	//confusing output
	//[{Book isbn = 12345	 Title = book1}, {Book isbn = 23145	 Title = book2}, Magazine issn = 4315	 Magazine Title = magazine]
	//[{Book isbn = 12345	 Title = book1}, {Book isbn = 23145	 Title = book2}, Magazine issn = 4315	 Magazine Title = magazine]
    //[{Book isbn = 12345	 Title = book1}, {Book isbn = 23145	 Title = book2}, Magazine issn = 4315	 Magazine Title = magazine]

}
