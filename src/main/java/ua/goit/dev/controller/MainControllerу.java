package ua.goit.dev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainControllerу {


	@GetMapping("/")
	public String mainPage(){

		return "main";
	}

}
