package com.couple.web.controllers;

import java.util.Collections;
import java.util.List;

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

import com.couple.model.AnswerOption;
import com.couple.services.CategoryService;

import com.couple.services.ResultsService;
import com.couple.services.external.SocialApiService;
import com.couple.web.dto.SurveyAnswers;


@Controller
@RequestMapping("/survey")
public class SurveyController {

	private CategoryService categoryService;

	private ResultsService resultsService;
	
	private SocialApiService socialApiService;

	@Autowired
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	@Autowired
	public void setSocialApiService(SocialApiService socialApiService) {
		this.socialApiService = socialApiService;
	}

	@Autowired
	public void setResultsService(ResultsService resultsService) {
		this.resultsService = resultsService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getSurveyForm(@RequestParam("myId") String myId, @RequestParam("partnerId") String partnerId) {
		ModelAndView modelAndView = new ModelAndView("survey");
		modelAndView.addObject("categories", categoryService.getCategories());

		modelAndView.addObject("me", socialApiService.getUser(myId));
		modelAndView.addObject("partner", socialApiService.getUser(partnerId));

		return modelAndView;
	}

	@RequestMapping(value = "/{myId}/{partnerId}", method = RequestMethod.POST)
	public View completeSurvey(@PathVariable("myId") String myId, @PathVariable("partnerId") String partnerId, HttpServletRequest request) {
		SurveyAnswers answers = new SurveyAnswers(myId, partnerId);

		@SuppressWarnings("unchecked")
		List<String> parameterNames = (List<String>) Collections.list(request.getParameterNames());
		for(String name: parameterNames) {
			if (name.startsWith("question-")) {
				int questionId = Integer.valueOf(name.substring("question-".length()));
				AnswerOption questionAnswer = AnswerOption.valueOf(request.getParameter(name));

				answers.addAnswer(questionId, questionAnswer);
			}
		}

		long coupleId = resultsService.saveAnswers(answers);

		return new RedirectView("/survey-completed/" + myId + "/" + coupleId, true);
	}
}
