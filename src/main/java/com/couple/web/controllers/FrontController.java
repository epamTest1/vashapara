package com.couple.web.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class FrontController {
	
	@RequestMapping("/")
	public String enterApplication(HttpSession session, @RequestParam("api_url") String apiUrl, @RequestParam("viewer_id") String viewerId) {
		session.setAttribute("apiUrl", apiUrl);
		
		return "redirect:choose-partner/" + viewerId;
	}

}
