package com.presentation;



import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/")
public class IndexController {

	@RequestMapping("/")
	public String index(){
		return "index";
	}
	
}
