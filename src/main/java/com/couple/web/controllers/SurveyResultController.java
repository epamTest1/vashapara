package com.couple.web.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.couple.services.ResultsService;

@Controller
@RequestMapping("/survey-completed")
public class SurveyResultController {

	private ChoosePartner choosePartner;

	private ResultsService resultsService;

	@Autowired
	public void setChoosePartner(ChoosePartner choosePartner) {
		this.choosePartner = choosePartner;
	}

	@Autowired
	public void setResultsService(ResultsService resultsService) {
		this.resultsService = resultsService;
	}

	@RequestMapping(value="/{coupleId}", method=RequestMethod.GET)
	public ModelAndView getResultView(@PathVariable("coupleId") String coupleId) {

		ModelAndView modelAndView = new ModelAndView("survey-completed");

		modelAndView.addObject("positiveAnswers", resultsService.getResults(true));
		modelAndView.addObject("negativeAnswers", resultsService.getResults(false));

		//JUst for test only
		String myId = "168962961";
		String partnerId = "170020609";

		try {
			modelAndView.addObject("me", choosePartner.getUser(myId));
			modelAndView.addObject("partner", choosePartner.getUser(partnerId));
		}
		catch (IOException e) {
		}

		int percent = new Random().nextInt(100);
		modelAndView.addObject("percent", percent);
		try {
			modelAndView.addObject("judgment", getJudgment(percent));
		}
		catch (UnsupportedEncodingException e) {
		}

		return modelAndView;
	}

	private String getJudgment(int percent) throws UnsupportedEncodingException {
		String in = null;
		if (percent < 15) {
			in = new String("Вы совершенно не понимаете друг друга. Но противоположности притягиваются, не правда ли ?".getBytes("windows-1251"), "UTF8");
		} else if (percent < 30) {
			in = new String("Уровень взаимопонимания между вами крайне низок. Возможно, кто то из вас очень эгоистичен.".getBytes("windows-1251"), "UTF8");;
		} else if (percent < 45) {
			in = new String("Каждый из Вас считает что именно он делает все, а партнер лентяй. Нехватка откровенности ?".getBytes("windows-1251"), "UTF8");;
		} else if (percent < 60) {
			in = new String("Вы типичная современная пара. Обходите острые углы, ловя счастливые моменты.".getBytes("windows-1251"), "UTF8");;
		} else if (percent < 75) {
			in = new String("Ваши отношения просто отличные. Похоже, что Вы отлично знаете друг друга.".getBytes("windows-1251"), "UTF8");;
		} else if (percent < 90) {
			in = new String("Крайне крепкая пара. Просто молодцы.".getBytes("windows-1251"), "UTF8");;
		} else {
			in = new String("Уникально. Такое взаимопонимание крайне редко встречается. Похоже, вы нашли свою половинку.".getBytes("windows-1251"), "UTF8");;
		}
		return in;
	}
}
