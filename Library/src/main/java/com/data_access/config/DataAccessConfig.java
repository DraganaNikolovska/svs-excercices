package com.data_access.config;

import com.data_access.*;
import com.domain.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PreDestroy;

/**
 * Created by dragana.nikolovska on 9/27/2016.
 */
@Configuration
public class DataAccessConfig {
    @Bean
    public SessionFactory getSessionFactory() {
        org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
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
    @Bean
    public BookDao bookDao(){
        return new HibernateBookDao(getSessionFactory());
    }
    @Bean
    public MagazineDao magazineDao(){
        return new HibernateMagazineDao(getSessionFactory());
    }
    @Bean
    public PublicationDao publicationDao(){
        return new HibernatePublicationDao(getSessionFactory());
    }
    @Bean
    public LoanDao loanDao(){
        return new HibernateLoanDao(getSessionFactory());
    }
    @Bean
    public MemberDao memberDao(){
        return new HibernateMemberDao(getSessionFactory());
    }
}
