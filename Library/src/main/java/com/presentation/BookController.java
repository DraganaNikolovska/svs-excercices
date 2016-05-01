package com.presentation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.data_access.HibernateBookDao;
import com.domain.Book;
import com.domain.Entity;
import com.domain.Loan;
import com.domain.LoanModel;
import com.domain.Member;
import com.domain.Publication;
import com.services.LibraryService;

import exceptions.NonExistingMember;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/books")
public class BookController {

	@Autowired
	private LibraryService libraryService;

	@ModelAttribute("books")
	public List<Entity> books() {
		return libraryService.listRegisteredBooks();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String listBooks() {
		return "books";
	}

	@ModelAttribute("book")
	Book book() {
		return new Book();
	}

	@RequestMapping(method = RequestMethod.POST)
	public String registerOrUpdateBook(@ModelAttribute("book") Book book) {
		if (libraryService.findBookByIsbn(book.getIsbn()) == null) {
			libraryService.registerBook(book.getIsbn(), book.getTitle());
		} else {
			libraryService.updateBookRegistrations(book.getIsbn(), book.getTitle());
		}
		return "redirect:/books";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String editBook(@PathVariable("id") Integer id, Model model) {
		final Book book = libraryService.findBookById(id);
		model.addAttribute("book", book);
		return "books";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody String deleteBook(@RequestParam(value = "book_id") Integer book_id) {
		libraryService.unregisterBook(book_id);
		return "{}";
	}

	@ModelAttribute("loan")
	LoanModel getLoan() {
		return new LoanModel();
	}

	@RequestMapping(value = "/lend", method = RequestMethod.POST)
	public String lend(@ModelAttribute("loan") LoanModel loan) {
		if(libraryService.findMemberByEmail(loan.getMemberEmail()) == null){
			throw new NonExistingMember(loan.getMemberEmail());
		}
		libraryService.lendBook(loan.getMemberEmail(), loan.getIsbn(), loan.getStartDate(), loan.getEndDate());	
		return "redirect:/books";
	}
	
	
	@ExceptionHandler(value = NonExistingMember.class)
	public String handleNonExistingMember(){		
		return "NonExistingMember";
	}
}
