package com.data_access;

import java.util.ArrayList;
import java.util.List;


import lombok.AllArgsConstructor;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import com.domain.Entity;
import com.domain.Member;

@AllArgsConstructor
public class HibernateMemberDao implements MemberDao {

    private SessionFactory sessionFactory;

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


    }

    public List<Entity> listAll() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<Entity> members = new ArrayList<Entity>();
        try {
            tx = session.beginTransaction();
            String hql = "FROM Member m ORDER BY m.id";
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

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(Member.class);
            Member x = (Member) cr.add(Restrictions.eq("id", id)).uniqueResult();
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

    @Override
    public Entity findById(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Criteria cr = session.createCriteria(Member.class);
            Member m = (Member) cr.add(Restrictions.eq("id", id)).uniqueResult();
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
