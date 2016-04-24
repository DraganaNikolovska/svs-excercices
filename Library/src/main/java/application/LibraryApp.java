package application;

import java.util.Date;
import java.util.Scanner;

import org.hibernate.SessionFactory;

import data_access.HibernateBookDao;
import data_access.HibernateConfiguration;
import data_access.HibernateLoanDao;
import data_access.HibernateMagazineDao;
import data_access.HibernateMemberDao;
import data_access.JDBCBookDao;
import data_access.MyDriverManager;
/*import data_access.JDBCBookDao;*/
import services.LibraryService;

public class LibraryApp {

	public enum implementation {
		JDBC, Hibernate
	}

	public static void main(String[] args) {

		implementation impl = choseImplementation();
		if (impl == implementation.Hibernate) {
			hibernateMenu();
		} else {
			jdbcMenu();
		}

	}

	private static void jdbcMenu() {
		LibraryService service = new LibraryService(new JDBCBookDao());
		
		Scanner keyBoardScanner = new Scanner(System.in);
		String line = "";
		String title, email, issn, isbn, y_n;
		
		listJDBCLibraryOptions();
		while (!(line = keyBoardScanner.nextLine()).equalsIgnoreCase("quit")) {
			switch (line) {
			case "1":
				System.out.println("Enter isbn");
				isbn = keyBoardScanner.nextLine();
				System.out.println("Enter title");
				title = keyBoardScanner.nextLine();
				service.registerBook(isbn, title);
				break;
			case "2":
				System.out.println("Enter book`s isbn");
				isbn = keyBoardScanner.nextLine();
				System.out.println("Enter new book title");
				title = keyBoardScanner.nextLine();
				service.updateBookRegistrations(isbn, title);
				break;
			case "3":
				System.out.println("Enter book`s isbn");
				isbn = keyBoardScanner.nextLine();
				service.unregisterBook(isbn);
				break;
			case "4":
				service.listRegisteredBooks();
				break;
			default:
				break;
			}
		}

	}

	private static void listJDBCLibraryOptions() {
		System.out.println("1: Register book");
		System.out.println("2: Update book");
		System.out.println("3: Delete book");
		System.out.println("4: List all books");
		System.out.println("Enter quit to quit the application");

	}

	private static void hibernateMenu() {

		SessionFactory sessionFactory = HibernateConfiguration.getSessionFactory();
		LibraryService service = new LibraryService(new HibernateBookDao(sessionFactory),
				new HibernateMemberDao(sessionFactory), new HibernateMagazineDao(sessionFactory),
				new HibernateLoanDao(sessionFactory));

		hibernateLibraryOptions();

		Scanner keyBoardScanner = new Scanner(System.in);
		String line = "";
		String title, email, issn, isbn, y_n;
		int id;

		while (!(line = keyBoardScanner.nextLine()).equalsIgnoreCase("quit")) {
			switch (line) {
			case "1":
				System.out.println("Enter isbn");
				isbn = keyBoardScanner.nextLine();
				System.out.println("Enter title");
				title = keyBoardScanner.nextLine();
				service.registerBook(isbn, title);
				break;
			case "2":
				System.out.println("Enter isbn");
				issn = keyBoardScanner.nextLine();
				System.out.println("Enter title");
				title = keyBoardScanner.nextLine();
				service.registerMagazine(issn, title);
				break;
			case "3":
				System.out.println("Enter name");
				String name = keyBoardScanner.nextLine();
				System.out.println("Enter email");
				email = keyBoardScanner.nextLine();
				service.registerMember(name, email);
				break;
			case "4":
				System.out.println("Enter member`s email");
				email = keyBoardScanner.nextLine();
				System.out.println("Enter book`s isbn");
				isbn = keyBoardScanner.nextLine();
				service.lendBook(email, isbn, new Date(), new Date());
				break;
			case "5":
				System.out.println("Enter member`s email");
				email = keyBoardScanner.nextLine();
				System.out.println("Enter magazine`s issn");
				issn = keyBoardScanner.nextLine();
				service.lendMagazine(email, issn, new Date(), new Date());
				break;
			case "6":
				System.out.println("Enter book`s isbn");
				isbn = keyBoardScanner.nextLine();
				System.out.println("Enter new book title");
				title = keyBoardScanner.nextLine();
				service.updateBookRegistrations(isbn, title);
				break;
			case "7":
				System.out.println("Enter magazine`s issn");
				issn = keyBoardScanner.nextLine();
				System.out.println("Enter new magazine title");
				title = keyBoardScanner.nextLine();
				service.updateBookRegistrations(issn, title);
				break;
			case "8":
				System.out.println("Enter book`s isbn");
				isbn = keyBoardScanner.nextLine();
				service.unregisterBook(isbn);
				break;
			case "9":
				System.out.println("Enter magazine`s issn");
				issn = keyBoardScanner.nextLine();
				service.unregisterMagazine(issn);
				break;
			case "10":
				System.out.println("Enter loan`s id");
				id = keyBoardScanner.nextInt();
				service.deleteLoan(id);
				break;
			case "11":
				System.out.println("Enter user`s email");
				email = keyBoardScanner.nextLine();
				service.unregisterMember(email);
				break;
			case "12":
				service.listRegisteredBooks();
				break;
			case "13":
				service.listRegisteredMagazines();
				break;
			case "14":
				service.listRegisteredLoans();
				break;
			case "15":
				service.listRegisteredMembers();
				break;
			default:
				break;
			}
			hibernateLibraryOptions();
		}
		sessionFactory.close();

	}

	private static void hibernateLibraryOptions() {
		System.out.println("1: Register book");
		System.out.println("2. Register magazine");
		System.out.println("3. Register member");
		System.out.println("4. Lend book");
		System.out.println("5. Lend magazine");
		System.out.println("6. Update book");
		System.out.println("7. Update magazine");
		System.out.println("8. Delete book");
		System.out.println("9. Delete magazine");
		System.out.println("10. Delete loan");
		System.out.println("11. Delete member");
		System.out.println("12. List all books");
		System.out.println("13. List all magazines");
		System.out.println("14. List all Loans");
		System.out.println("15. List all members");
		System.out.println("Enter quit to quit the application");

	}

	private static implementation choseImplementation() {
		System.out.println("Chose implementation");
		System.out.println("Enter 1 for JDBC Implementation");
		System.out.println("Enter 2 for Hibernate Implementation");
		Scanner scanner = new Scanner(System.in);
		Integer choise = scanner.nextInt();
		if (choise == 1)
			return implementation.JDBC;

		return implementation.Hibernate;

	}
}
