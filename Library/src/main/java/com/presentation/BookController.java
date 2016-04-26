package com.presentation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	private HibernateBookDao hibernateBookRepository;

	@ModelAttribute("books")
	public List<Entity> books() {
		return hibernateBookRepository.listAll();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String listBooks() {
		return "books";
	}
	@ModelAttribute("book")
	Book book(){
		return new Book();
	}
	@RequestMapping(method = RequestMethod.POST)
	public String registerOrUpdateBook(@ModelAttribute("book") Book book) {
		if (book.getId() == null) {
			hibernateBookRepository.insert(book);
		} else {
			hibernateBookRepository.update(book);
		}
		return "redirect:/books";
	}

}
