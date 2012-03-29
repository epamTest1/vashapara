package com.couple.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.couple.services.CategoryService;
import com.couple.web.dto.User;

@Controller
@RequestMapping("/survey")
public class SurveyController {
	
	private CategoryService categoriesService;
	
	@Autowired
	public void setCategoriesService(CategoryService categoriesService) {
		this.categoriesService = categoriesService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getSurveyForm(@RequestParam("myId") String myId, @RequestParam("partnerId") String partnerId) {
		ModelAndView modelAndView = new ModelAndView("survey");
		modelAndView.addObject("categories", categoriesService.getCategories());
		modelAndView.addObject("me", new User(myId, "Ваня", "http://placehold.it/200x260"));
		modelAndView.addObject("partner", new User(partnerId, "Аня", "http://placeboobs.com/200/260"));
		
		return modelAndView;
	}
}
