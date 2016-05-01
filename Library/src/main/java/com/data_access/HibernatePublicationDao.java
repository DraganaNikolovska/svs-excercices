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
import com.domain.Magazine;
import com.domain.Publication;
@Repository
public class HibernatePublicationDao implements PublicationDao {

	private SessionFactory sessionFactory;

	@Autowired
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
			String hql = "FROM Publication p ORDER BY p.id";
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

	@Override
	public Publication getPublication(Integer id) {
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try {
			System.out.println("id = " + id);
			tx = session.beginTransaction();
			Criteria cr = session.createCriteria(Publication.class);
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
