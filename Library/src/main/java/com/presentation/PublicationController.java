package com.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.data_access.HibernatePublicationDao;
import com.domain.Publication;
import com.services.LibraryService;

@Controller
@RequestMapping("publications")
public class PublicationController {

	@Autowired
	private LibraryService service;

	@RequestMapping(method = RequestMethod.GET)
	String publications() {
		return "publications";
	}

	@ModelAttribute("publications")
	List<Publication> listPublications() {
		return service.listRegisteredPublications();
	}
}
