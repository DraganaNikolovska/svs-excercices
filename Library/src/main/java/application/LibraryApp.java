package application;

import org.hibernate.SessionFactory;

import data_access.HibernateBookDao;
import data_access.HibernateConfiguration;
import data_access.HibernateMemberDao;
import data_access.JDBCBookDao;
/*import data_access.JDBCBookDao;*/
import services.LibraryService;

public class LibraryApp {

	public static void main(String[] args) {

		// JDBC scenario has only support for Book manipulation
		LibraryService jdbcService = new LibraryService(new JDBCBookDao(), null);
		jdbcService.registerBook("123", "Clean Code");
		jdbcService.updateBookRegistrations("123", "Clean code 1");
		jdbcService.unregisterBook("123");
		jdbcService.listRegisteredBooks();

		// Hibernate scenario
		SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
		LibraryService hibernateService = new LibraryService(new HibernateBookDao(sessionFactory),
				new HibernateMemberDao(sessionFactory));

		hibernateService.registerMember("Dragana", "dragana@gmail.com");
		hibernateService.unregisterMember("dragana@gmail.com");
		hibernateService.registerBook("123", "Clean code");
		hibernateService.listRegisteredMembers();
		hibernateService.listRegisteredBooks();
		
		sessionFactory.close();

	}
}
