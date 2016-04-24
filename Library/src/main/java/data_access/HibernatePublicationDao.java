/*package data_access;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import domain.Book;
import domain.Entity;
import domain.Publication;

public class HibernatePublicationDao implements Dao {

	private SessionFactory sessionFactory;

	public HibernatePublicationDao(SessionFactory s) {
		this.sessionFactory = s;
	}

	public void insert(Entity entity) {
		// TODO Auto-generated method stub

	}

	public void update(Entity entity) {
		// TODO Auto-generated method stub

	}

	public void delete(Entity entity) {
		// TODO Auto-generated method stub

	}

	public List<Entity> listAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public Entity get(String isbnOrIssn) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Publication p = null;
		try {
			tx = session.beginTransaction();
			String hql = "from (select * from Book b where b.isbn = :isbnOrIssn) as tmp union "
					+ "(select * from Magazine m where m.issn = :isbnOrIssn)";
			Query query = session.createQuery(hql);
			query.setParameter("isbn", isbnOrIssn);
			query.setParameter("issn", isbnOrIssn);
			p = (Publication) query.uniqueResult();
			tx.commit();
			return p;
		} catch (RuntimeException e) {
			if (tx != null)
				tx.rollback();
			throw e;
		} finally {

			session.close();
		}
	}

}
*/