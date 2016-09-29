package com.presentation;


import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*@Controller*/
/*@Import(AppConfig.class)*/
@RequestMapping("/")
public class IndexController {

	@RequestMapping("/")
	public String index(){
		return "index";
	}
	
}
