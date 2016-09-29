package com.presentation;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.domain.Entity;
import com.services.LibraryService;


@RequestMapping("/loans")

public class LoanController {

	//@Autowired
	private LibraryService libraryService;

	public LoanController(LibraryService libraryService){
		this.libraryService = libraryService;
	}

	@ModelAttribute("loans")
	public List<Entity> loans() {
		return libraryService.listRegisteredLoans();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String listLoans() {
		return "loans";
	}
}
