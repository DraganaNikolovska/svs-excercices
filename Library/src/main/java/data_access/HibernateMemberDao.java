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

public class HibernateMemberDao implements Dao {

	private SessionFactory sessionFactory;

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

	public void delete(Entity entity) {
		System.out.println("delete!!!!!");
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(Member.class);
			Member m = (Member) entity;
			Member x = (Member) cr.add(Restrictions.eq("email", m.getEmail())).uniqueResult();
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

}
