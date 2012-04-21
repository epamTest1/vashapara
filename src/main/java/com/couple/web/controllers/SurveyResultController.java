package com.couple.web.controllers;

import java.io.IOException;
import java.nio.charset.Charset;
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
		modelAndView.addObject("judgment", getJudgment(percent));

		return modelAndView;
	}

	private String getJudgment(int percent) {
		String in = null;
		if (percent < 15) {
			in = transformString("Вы совершенно не понимаете друг друга. Но противоположности притягиваются, не правда ли ?");
		} else if (percent < 30) {
			in = transformString("Уровень взаимопонимания между вами крайне низок. Возможно, кто то из вас очень эгоистичен.");
		} else if (percent < 45) {
			in = transformString("Каждый из Вас считает что именно он делает все, а партнер лентяй. Нехватка откровенности ?");
		} else if (percent < 60) {
			in = transformString("Вы типичная современная пара. Обходите острые углы, ловя счастливые моменты.");
		} else if (percent < 75) {
			in = transformString("Ваши отношения просто отличные. Похоже, что Вы отлично знаете друг друга.");
		} else if (percent < 90) {
			in = transformString("Крайне крепкая пара. Просто молодцы.");
		} else {
			in = transformString("Уникально. Такое взаимопонимание крайне редко встречается. Похоже, вы нашли свою половинку.");
		}
		return in;
	}
	
	private String transformString(String s) {
		return new String(s.getBytes(Charset.forName("windows-1251")), Charset.forName("UTF-8"));
	}
}
