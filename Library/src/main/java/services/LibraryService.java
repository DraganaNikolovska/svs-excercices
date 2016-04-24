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

import data_access.Dao;
import data_access.HibernateBookDao;

public class LibraryService {

	private Dao bookDao;
	private Dao memberDao;
	private Dao magzineDao;
	private Dao loanDao;

	public LibraryService(Dao bookDao, Dao memberDao, Dao magazineDao, Dao loanDao) {
		this.bookDao = bookDao;
		this.memberDao = memberDao;
		this.magzineDao = magazineDao;
		this.loanDao = loanDao;
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
	public void deleteLoan(Integer loanId){
		
		loanDao.delete(loanId);
	}
	public void lendBook(String email, String isbn, Date startDate, Date endDate) {
		Loan loan = new Loan();
		Member member = (Member) memberDao.get(email);
		if (member == null) {
			throw new NonExistingMember(email);
		}
		Book b = (Book) bookDao.get(isbn);
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
		Member member = (Member) memberDao.get(email);
		if (member == null) {
			throw new NonExistingMember(email);
		}
		Magazine m = (Magazine) magzineDao.get(issn);
		if (m == null) {
			throw new NonExistingMagazine(issn);
		}
		loan.setStartDate(startDate);
		loan.setEndDate(endDate);
		loan.setMember(member);
		loan.setPublication(m);
		loanDao.insert(loan);
	}

	public void lendPublication(String email, String isbnOrIssn) {

	}

}
