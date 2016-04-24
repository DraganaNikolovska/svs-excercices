package services;

import domain.Book;
import domain.Entity;
import domain.Loan;
import domain.Magazine;
import domain.Member;
import domain.Publication;
import exceptions.NonExistingBook;
import exceptions.NonExistingMagazine;
import exceptions.NonExistingMember;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import data_access.BookDao;
import data_access.Dao;
import data_access.HibernateBookDao;
import data_access.LoanDao;
import data_access.MagazineDao;
import data_access.MemberDao;
import data_access.PublicationDao;

public class LibraryService {

	private BookDao bookDao;
	private MemberDao memberDao;
	private MagazineDao magzineDao;
	private LoanDao loanDao;
	private PublicationDao publicationDao;

	public LibraryService(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	public LibraryService(BookDao bookDao, MemberDao memberDao, MagazineDao magazineDao, LoanDao loanDao, PublicationDao publicationDao) {
		this.bookDao = bookDao;
		this.memberDao = memberDao;
		this.magzineDao = magazineDao;
		this.loanDao = loanDao;
		this.publicationDao = publicationDao;
	}

	public void registerBook(String isbn, String title) {
		Book book = new Book();
		book.setIsbn(isbn);
		book.setTitle(title);
		bookDao.insert(book);

	}

	public void unregisterBook(String isbn) {
		Book b = new Book();
		b.setIsbn(isbn);
		bookDao.delete(b);
	}

	public void updateBookRegistrations(String isbn, String title) {
		Book b = new Book();
		b.setIsbn(isbn);
		b.setTitle(title);
		bookDao.update(b);
	}

	public void listRegisteredBooks() {
		ArrayList<Entity> books = (ArrayList<Entity>) bookDao.listAll();
		for (Entity entity : books) {
			System.out.println((Book) entity);
		}
	}

	public void registerMember(String name, String email) {
		Member member = new Member();
		member.setEmail(email);
		member.setName(name);
		memberDao.insert(member);
	}

	public void unregisterMember(String email) {
		Member member = new Member();
		member.setEmail(email);
		memberDao.delete(member);
	}

	public void listRegisteredMembers() {
		ArrayList<Entity> members = (ArrayList<Entity>) memberDao.listAll();
		for (Entity entity : members) {
			System.out.println((Member) entity);
		}
	}

	public void registerMagazine(String issn, String title) {
		Magazine magazine = new Magazine();
		magazine.setIssn(issn);
		magazine.setTitle(title);
		magzineDao.insert(magazine);

	}

	public void unregisterMagazine(String issn) {
		Magazine m = new Magazine();
		m.setIssn(issn);
		magzineDao.delete(m);
	}

	public void updateMagazineRegistrations(String issn, String title) {
		Magazine m = new Magazine();
		m.setIssn(issn);
		m.setTitle(title);
		magzineDao.update(m);
	}

	public void listRegisteredMagazines() {
		ArrayList<Entity> magazines = (ArrayList<Entity>) magzineDao.listAll();
		for (Entity entity : magazines) {
			System.out.println((Magazine) entity);
		}
	}

	public void listRegisteredLoans() {
		ArrayList<Entity> loans = (ArrayList<Entity>) loanDao.listAll();
		for (Entity entity : loans) {
			System.out.println((Loan) entity);
		}
	}

	public void deleteLoan(Integer loanId) {
		loanDao.delete(loanId);
	}

	public void lendBook(String email, String isbn, Date startDate, Date endDate) {
		Loan loan = new Loan();
		Member member = (Member) memberDao.findByEmail(email);
		if (member == null) {
			throw new NonExistingMember(email);
		}
		Book b = (Book) bookDao.findByIsbn(isbn);
		if (b == null) {
			throw new NonExistingBook(isbn);
		}
		loan.setStartDate(startDate);
		loan.setEndDate(endDate);
		loan.setMember(member);
		loan.setPublication(b);
		loanDao.insert(loan);

	}

	public void lendMagazine(String email, String issn, Date startDate, Date endDate) {
		Loan loan = new Loan();
		Member member = (Member) memberDao.findByEmail(email);
		if (member == null) {
			throw new NonExistingMember(email);
		}
		Magazine m = (Magazine) magzineDao.fineByIssn(issn);
		if (m == null) {
			throw new NonExistingMagazine(issn);
		}
		loan.setStartDate(startDate);
		loan.setEndDate(endDate);
		loan.setMember(member);
		loan.setPublication(m);
		loanDao.insert(loan);
	}

	public void listRegisteredPublications(){
		List<Publication> p = publicationDao.listAll();
		for (Publication publication : p) {
			System.out.println(p);
		}
	}
	

}
