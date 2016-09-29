package com.presentation;

import java.util.List;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.*;

import com.domain.Book;
import com.domain.Entity;
import com.services.LibraryService;

@RestController
@RequestMapping("/api/books")
@AllArgsConstructor
public class BookRestController {

	LibraryService libraryService;

	@RequestMapping(method = RequestMethod.GET)
	public List<Entity> listBooks() {
		return libraryService.listRegisteredBooks();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Book book(@PathVariable("id") Integer id) {

		return libraryService.findBookById(id);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public Book registerBook(@RequestBody Book book) {
		libraryService.registerBook(book.getIsbn(), book.getTitle());
		return book;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Book updateBookRegistration(@RequestBody Book book, @PathVariable("id") Integer id) {
		book.setId(id);
		libraryService.updateBookRegistrations(book.getIsbn(), book.getTitle());
		return book;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String unregisterBook(@PathVariable("id") Integer id) {
		libraryService.unregisterBook(id);
		return "Success";
	}
}
