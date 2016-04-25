package application;

import java.util.Date;
import java.util.Scanner;

import org.hibernate.SessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ComponentScan;

import data_access.HibernateBookDao;
import data_access.HibernateConfiguration;
import data_access.HibernateLoanDao;
import data_access.HibernateMagazineDao;
import data_access.HibernateMemberDao;
import data_access.HibernatePublicationDao;
import data_access.JDBCBookDao;
import data_access.MyDriverManager;
import presentation.LibraryController;
/*import data_access.JDBCBookDao;*/
import services.LibraryService;

@SpringBootApplication
public class LibraryApp implements CommandLineRunner, ApplicationContextAware {

	ApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(LibraryApp.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {
		// ApplicationContext context = ??
		context.getBean(LibraryController.class).run();

	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		this.context = arg0;

	}

}
