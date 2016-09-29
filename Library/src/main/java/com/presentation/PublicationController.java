package com.presentation;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.data_access.HibernatePublicationDao;
import com.domain.Book;
import com.domain.Magazine;
import com.domain.Publication;
import com.services.LibraryService;

/*@Controller*/
@RequestMapping("publications")
/*@Import(AppConfig.class)*/
public class PublicationController {

	@Autowired
	private LibraryService libraryService;

	public PublicationController(LibraryService libraryService){
		this.libraryService = libraryService;
	}


	@RequestMapping(method = RequestMethod.GET)
	String publications() {
		return "publications";
	}

	@ModelAttribute("publications")
	List<Publication> listPublications() {
		return libraryService.listRegisteredPublications();
	}
	@ModelAttribute("publication")
	Publication publication(){
		return new Book();
	}
}
