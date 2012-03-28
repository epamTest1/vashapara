package com.couple.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.couple.model.Category;
import com.couple.model.Question;
import com.couple.web.dto.User;

@Controller
@RequestMapping("/survey")
public class SurveyController {
	
	private final List<Category> categories;
	
	public SurveyController() {
		Category home = new Category(1, "Дом");
		home.addQuestion(new Question(1, "Убираю в квартире"));
		home.addQuestion(new Question(2, "Мою посуду"));
		home.addQuestion(new Question(3, "Выношу мусор"));
		home.addQuestion(new Question(4, "Мою окна"));
		
		Category finances = new Category(2, "Финансы");
		finances.addQuestion(new Question(5, "бла-бла-бла"));
		
		categories = Arrays.asList(home, finances);
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getSurveyForm() {
		return getSurveyForm("test", "test");
	}
	
	@RequestMapping(value="/{myId}/{partnerId}", method = RequestMethod.GET)
	public ModelAndView getSurveyForm(@PathVariable("myId") String myId, @PathVariable("partnerId") String partnerId) {
		ModelAndView modelAndView = new ModelAndView("survey");
		modelAndView.addObject("categories", categories);
		modelAndView.addObject("me", new User(myId, "Ваня", "http://placehold.it/200x260"));
		modelAndView.addObject("partner", new User(partnerId, "Аня", "http://placeboobs.com/200/260"));
		
		return modelAndView;
	}
}
