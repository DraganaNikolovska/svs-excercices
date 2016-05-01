package com.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.domain.Entity;
import com.domain.LoanModel;
import com.domain.Magazine;
import com.services.LibraryService;

import exceptions.NonExistingMember;

import com.data_access.HibernateMagazineDao;

@Controller
@RequestMapping("/magazines")
public class MagazineController {

	@Autowired
	private LibraryService libraryService;

	@RequestMapping(method = RequestMethod.GET)
	String magazines() {
		return "magazines";
	}

	@ModelAttribute("magazines")
	public List<Entity> listMagazines() {
		return libraryService.listRegisteredMagazines();
	}

	@ModelAttribute("magazine")
	Magazine magazine() {
		return new Magazine();
	}

	@RequestMapping(method = RequestMethod.POST)
	public String registerOrUpdateMagazine(@ModelAttribute("magazine") Magazine magazine) {
		if (libraryService.findMagazineByIssn(magazine.getIssn())== null) {
			libraryService.registerMagazine(magazine.getIssn(), magazine.getTitle());
		} else {
			libraryService.updateMagazineRegistrations(magazine.getIssn(), magazine.getTitle());
		}
		return "redirect:/magazines";
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String editMagazine(@PathVariable("id") Integer id, Model model){
		Magazine magazine = libraryService.findMagazineById(id);
		model.addAttribute("magazine", magazine);
		return "magazines";
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
		libraryService.lendMagazine(loan.getMemberEmail(), loan.getIsbn(), loan.getStartDate(), loan.getEndDate());	
		return "redirect:/magazines";
	}
}
