package com.data_access;

import javax.annotation.PreDestroy;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;

import com.domain.Book;
import com.domain.Entity;
import com.domain.Loan;
import com.domain.Magazine;
import com.domain.Member;
import com.domain.MemberShip;
import com.domain.Publication;

@org.springframework.context.annotation.Configuration
public class HibernateConfiguration {

	@Bean
	public SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.addAnnotatedClass(Book.class).addAnnotatedClass(Magazine.class)
				.addAnnotatedClass(Entity.class).addAnnotatedClass(Publication.class).addAnnotatedClass(Member.class)
				.addAnnotatedClass(MemberShip.class).addAnnotatedClass(Loan.class).buildSessionFactory(serviceRegistry);
		return sessionFactory;
	}

	@PreDestroy
	public void closeSessionFactory() {
		getSessionFactory().close();
	}
}
