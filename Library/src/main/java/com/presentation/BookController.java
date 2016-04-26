package com.presentation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.data_access.HibernateBookDao;
import com.domain.Book;
import com.domain.Entity;
import com.services.LibraryService;

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
		System.out.println("--------" + book);
		model.addAttribute("book", book);
		return "books";
	}

}
