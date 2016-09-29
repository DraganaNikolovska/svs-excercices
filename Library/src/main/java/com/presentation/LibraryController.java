package com.presentation;

import java.util.Date;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.services.LibraryService;



public class LibraryController {

	private LibraryService service;
	private Scanner keyBoardScanner;

	//@Autowired
	public LibraryController(LibraryService service) {
		this.service = service;
		this.keyBoardScanner = new Scanner(System.in);
	}

	public void run() {

		String  title, email, issn, isbn;
		int line;
		int id;

		showMenuOptions();
		while (  (line = Integer.parseInt(keyBoardScanner.nextLine())) != -1) {
			switch (line) {
				case 1:
				System.out.println("Enter isbn");
				isbn = keyBoardScanner.nextLine();
				System.out.println("Enter title");
				title = keyBoardScanner.nextLine();
				service.registerBook(isbn, title);
				break;
			case 2:
				System.out.println("Enter issn");
				issn = keyBoardScanner.nextLine();
				System.out.println("Enter title");
				title = keyBoardScanner.nextLine();
				service.registerMagazine(issn, title);
				break;
			case 3:
				System.out.println("Enter name");
				String name = keyBoardScanner.nextLine();
				System.out.println("Enter email");
				email = keyBoardScanner.nextLine();
				service.registerMember(name, email);
				break;
			case 4:
				System.out.println("Enter member`s email");
				email = keyBoardScanner.nextLine();
				System.out.println("Enter book`s isbn");
				isbn = keyBoardScanner.nextLine();
				service.lendBook(email, isbn, new Date(), new Date());
				break;
			case 5:
				System.out.println("Enter member`s email");
				email = keyBoardScanner.nextLine();
				System.out.println("Enter magazine`s issn");
				issn = keyBoardScanner.nextLine();
				service.lendMagazine(email, issn, new Date(), new Date());
				break;
			case 6:
				System.out.println("Enter book`s isbn");
				isbn = keyBoardScanner.nextLine();
				System.out.println("Enter new book title");
				title = keyBoardScanner.nextLine();
				service.updateBookRegistrations(isbn, title);
				break;
			case 7:
				System.out.println("Enter magazine`s issn");
				issn = keyBoardScanner.nextLine();
				System.out.println("Enter new magazine title");
				title = keyBoardScanner.nextLine();
				service.updateBookRegistrations(issn, title);
				break;
			case 8:
				System.out.println("Enter book`s id");
				id = keyBoardScanner.nextInt();
				service.unregisterBook(id);
				break;
			case 9:
				System.out.println("Enter magazine`s id");
				id = keyBoardScanner.nextInt();
				service.unregisterMagazine(id);
				break;
			case 10:
				System.out.println("Enter loan`s id");
				id = keyBoardScanner.nextInt();
				service.deleteLoan(id);
				break;
			case 11:
				System.out.println("Enter user`s id");
				id = keyBoardScanner.nextInt();
				service.unregisterMember(id);
				break;
			case 12:
				service.listRegisteredBooks();
				break;
			case 13:
				service.listRegisteredMagazines();
				break;
			case 14:
				service.listRegisteredLoans();
				break;
			case 15:
				service.listRegisteredMembers();
				break;
			case 16:
				service.listRegisteredPublications();
				break;
			case 17:
				service.findMagazineById(5);
				break;
			default:
				break;
			}
			showMenuOptions();
		}
	}

	private void showMenuOptions() {
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
		System.out.println("16. List all publications");
		System.out.println("Enter -1 to quit the application");
	}
}
