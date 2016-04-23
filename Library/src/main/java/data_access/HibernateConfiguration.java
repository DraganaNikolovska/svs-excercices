package data_access;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import domain.Book;
import domain.Entity;
import domain.Loan;
import domain.Magazine;
import domain.Member;
import domain.MemberShip;
import domain.Publication;

public class HibernateConfiguration {

	public static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.addAnnotatedClass(Book.class)
				.addAnnotatedClass(Magazine.class).addAnnotatedClass(Entity.class)
				.addAnnotatedClass(Publication.class)
				.addAnnotatedClass(Member.class)
				.addAnnotatedClass(MemberShip.class)
				.addAnnotatedClass(Loan.class)
				.buildSessionFactory(serviceRegistry);
		return sessionFactory;
	}
}
