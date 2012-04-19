package com.couple.web.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/survey-completed")
public class SurveyResultController {

	private ChoosePartner choosePartner;

	@Autowired
	public void setChoosePartner(ChoosePartner choosePartner) {
		this.choosePartner = choosePartner;
	}

	@RequestMapping(value="/{coupleId}", method=RequestMethod.GET)
	public ModelAndView getResultView(@PathVariable("coupleId") String coupleId) {

		ModelAndView modelAndView = new ModelAndView("survey-completed");
		//modelAndView.addObject("categories", categoryService.getCategories());

		//JUst for test only
		String myId = "168962961";
		String partnerId = "170020609";

		try {
			modelAndView.addObject("me", choosePartner.getUser(myId));
			modelAndView.addObject("partner", choosePartner.getUser(partnerId));
		}
		catch (IOException e) {
		}

		return modelAndView;
	}
}
