package services;

import domain.Book;
import domain.Entity;
import domain.Magazine;
import domain.Member;

import java.util.ArrayList;
import java.util.List;

import data_access.Dao;
import data_access.HibernateBookDao;

public class LibraryService {

	private Dao bookDao;
	private Dao memberDao;

	public LibraryService(Dao bookDao, Dao memberDao) {
		this.bookDao = bookDao;
		this.memberDao = memberDao;
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
	/*
	 * public void listRegisteredBooks() { ArrayList<Book> books =
	 * dao.listAll(); for (Book book : books) { System.out.println(book); }
	 * 
	 * }
	 * 
	 * public void updateBookRegistrations(String isbn, String title) {
	 * dao.updateBook(isbn, title);
	 * 
	 * }
	 * 
	 * public void unregisterBooks(String isbn) { dao.deleteBook(isbn);
	 * 
	 * } public void registerMagazine(String issn, String title){
	 * dao.insertMagazine(issn, title); } public void
	 * updateMagazineRegistrations(String issn, String title) {
	 * dao.updateMagazine(issn, title);
	 * 
	 * }
	 * 
	 * public void unregisterMagazine(String issn) { dao.deleteMagazine(issn);
	 * 
	 * } public void listRegisteredMagazines() { List <Magazine> magazines =
	 * dao.listAllMagazines(); for (Magazine magazine : magazines) {
	 * System.out.println(magazine); }
	 * 
	 * }
	 */
}
