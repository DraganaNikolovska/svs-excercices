package data_access;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;

import domain.Book;
import domain.Entity;
import domain.Loan;
import domain.Magazine;
import domain.Member;
import domain.MemberShip;
import domain.Publication;

@org.springframework.context.annotation.Configuration
public class HibernateConfiguration {

	@Bean
	public static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.addAnnotatedClass(Book.class).addAnnotatedClass(Magazine.class)
				.addAnnotatedClass(Entity.class).addAnnotatedClass(Publication.class).addAnnotatedClass(Member.class)
				.addAnnotatedClass(MemberShip.class).addAnnotatedClass(Loan.class).buildSessionFactory(serviceRegistry);
		return sessionFactory;
	}

	public void closeSessionFactory() {
		getSessionFactory().close();
	}
}
