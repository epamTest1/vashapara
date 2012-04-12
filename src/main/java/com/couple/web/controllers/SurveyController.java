package com.couple.web.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.couple.services.CategoryService;

@Controller
@RequestMapping("/survey")
public class SurveyController {

	private CategoryService categoriesService;

	private ChoosePartner choosePartner;

	@Autowired
	public void setChoosePartner(ChoosePartner choosePartner) {
		this.choosePartner = choosePartner;
	}

	@Autowired
	public void setCategoriesService(CategoryService categoriesService) {
		this.categoriesService = categoriesService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getSurveyForm(@RequestParam("myId") String myId, @RequestParam("partnerId") String partnerId) {
		ModelAndView modelAndView = new ModelAndView("survey");
		modelAndView.addObject("categories", categoriesService.getCategories());

		try {
			modelAndView.addObject("me", choosePartner.getUser(myId));
			modelAndView.addObject("partner", choosePartner.getUser(partnerId));
		}
		catch (IOException e) {
		}

		return modelAndView;
	}

	@RequestMapping(value = "/{myId}/{partnerId}", method = RequestMethod.POST)
	public View completeSurvey(@PathVariable("myId") String myId, @PathVariable("partnerId") String partnerId, HttpServletRequest request) {
		return new RedirectView("/survey/{myId}/{partnerId}", true);
	}

	@RequestMapping(value = "/{myId}/{partnerId}", method = RequestMethod.GET)
	public String surveyResults(@PathVariable("myId") String myId, @PathVariable("partnerId") String partnerId) {
		return "survey-completed";
	}
}
