package application;

import org.hibernate.SessionFactory;

import data_access.HibernateBookDao;
import data_access.HibernateConfiguration;
import data_access.JDBCBookDao;
import services.LibraryService;

public class LibraryApp {

	public static void main(String[] args) {

		// JDBC scenario
		LibraryService JDBCService = new LibraryService(new JDBCBookDao());
		JDBCService.registerBook("12345", "Clean Code");
		JDBCService.listRegisteredBooks();

		// Hibernate scenario
		SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
		LibraryService hibernateService = new LibraryService(new HibernateBookDao(sessionFactory));

		hibernateService.registerBook("123", "book 1");
		hibernateService.updateBookRegistrations("123", "book 3");
		hibernateService.listRegisteredBooks();

		sessionFactory.close();

	}
}
