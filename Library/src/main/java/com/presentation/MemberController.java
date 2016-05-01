package com.presentation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.domain.Entity;
import com.domain.Member;
import com.services.LibraryService;

@Controller
@RequestMapping("/members")
public class MemberController {

	@Autowired
	private LibraryService libraryService;

	@ModelAttribute("member")
	Member member() {
		return new Member();
	}

	@ModelAttribute("members")
	public List<Entity> members() {
		return libraryService.listRegisteredMembers();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String listMembers() {
		return "members";
	}

	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String m(){
		return "members_registration";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@ModelAttribute Member member) {
		libraryService.registerMember(member.getName(), member.getEmail());
		return "redirect:/books";
	}

}
