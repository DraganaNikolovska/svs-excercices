package com.seavus.twitter.data_access;

import javax.annotation.PreDestroy;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;
import com.seavus.twitter.domain.Tweet;
import com.seavus.twitter.domain.TweeterUser;

@org.springframework.context.annotation.Configuration
public class HibernateConfiguration {

	@Bean
	public SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration();
		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		SessionFactory sessionFactory = configuration.addAnnotatedClass(TweeterUser.class).addAnnotatedClass(Tweet.class)
				.buildSessionFactory(serviceRegistry);
		return sessionFactory;
	}

	@PreDestroy
	public void closeSessionFactory() {
		getSessionFactory().close();
	}
}
