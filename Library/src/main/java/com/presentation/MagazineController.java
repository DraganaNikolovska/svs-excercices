package com.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.domain.Entity;
import com.services.LibraryService;
import com.data_access.HibernateMagazineDao;

@Controller
@RequestMapping("/magazines")
public class MagazineController {

	@Autowired
	private LibraryService service;

	@RequestMapping(method = RequestMethod.GET)
	String magazines() {
		return "magazines";
	}

	@ModelAttribute("magazines")
	public List<Entity> listMagazines() {
		return service.listRegisteredMagazines();
	}

}
