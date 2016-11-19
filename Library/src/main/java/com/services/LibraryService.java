package com.services;



import com.domain.Book;
import com.domain.Entity;
import com.domain.Loan;
import com.domain.Magazine;
import com.domain.Member;
import com.domain.Publication;
import exceptions.NonExistingBook;
import exceptions.NonExistingMagazine;
import exceptions.NonExistingMember;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.data_access.BookDao;
import com.data_access.LoanDao;
import com.data_access.MagazineDao;
import com.data_access.MemberDao;
import com.data_access.PublicationDao;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LibraryService {

	private BookDao bookDao;
	private MemberDao memberDao;
	private MagazineDao magzineDao;
	private LoanDao loanDao;
	private PublicationDao publicationDao;

	public void registerBook(String isbn, String title) {
		Book book = new Book();
		book.setIsbn(isbn);
		book.setTitle(title);
		bookDao.insert(book);

	}

	public void unregisterBook(Integer id) {
		bookDao.delete(id);
	}

	public void updateBookRegistrations(String isbn, String title) {
		bookDao.updateBookTitle(isbn, title);
	}

	public List<Entity> listRegisteredBooks() {
		ArrayList<Entity> books = (ArrayList<Entity>) bookDao.listAll();
		books.forEach(entity->
				System.out.println((Book)entity)
		);
		return books;
	}

	public void registerMember(String name, String email) {
		Member member = new Member();
		member.setEmail(email);
		member.setName(name);
		memberDao.insert(member);
	}

	public void unregisterMember(Integer id) {
		memberDao.delete(id);
	}

	public List<Entity> listRegisteredMembers() {
		ArrayList<Entity> members = (ArrayList<Entity>) memberDao.listAll();
		for (Entity entity : members) {
			System.out.println((Member) entity);
		}
		return members;
	}

	public void registerMagazine(String issn, String title) {
		Magazine magazine = new Magazine();
		magazine.setIssn(issn);
		magazine.setTitle(title);
		magzineDao.insert(magazine);

	}

	public void unregisterMagazine(Integer id) {
		magzineDao.delete(id);
	}

	public void updateMagazineRegistrations(String issn, String title) {

		magzineDao.updateMagazineTitle(issn, title);
	}

	public List<Entity> listRegisteredMagazines() {
		ArrayList<Entity> magazines = (ArrayList<Entity>) magzineDao.listAll();
		for (Entity entity : magazines) {
			System.out.println((Magazine) entity);
		}
		return magazines;
	}

	public List<Entity> listRegisteredLoans() {
		ArrayList<Entity> loans = (ArrayList<Entity>) loanDao.listAll();
		for (Entity entity : loans) {
			System.out.println((Loan) entity);
		}
		return loans;
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

	public List<Publication> listRegisteredPublications() {
		List<Publication> list = publicationDao.listAll();
		for (Publication publication : list) {
			System.out.println(publication);
		}
		return list;
	}

	public Book findBookByIsbn(String isbn) {
		return bookDao.findByIsbn(isbn);
	}

	public Book findBookById(Integer id) {
		return (Book) bookDao.findById(id);
	}

	public Magazine findMagazineById(Integer id) {
		Magazine m = (Magazine) magzineDao.findById(id);
		return m;
	}
	public Magazine findMagazineByIssn(String issn) {
		Magazine m = (Magazine) magzineDao.fineByIssn(issn);
		return m;
	}
	public Publication findPublication(Integer id){
		return publicationDao.getPublication(id);
	}
	public Member findMemberByEmail(String email){
		return memberDao.findByEmail(email);
	}

	public void unregisterLoan(Integer publication_id) {
		Publication p = publicationDao.getPublication(publication_id);
		loanDao.unregisterLoan(p);
		
	}
}
