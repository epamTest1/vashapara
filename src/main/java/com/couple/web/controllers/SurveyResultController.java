package com.couple.web.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.couple.services.ResultsService;
import com.couple.services.external.SocialApiService;
import com.couple.web.dto.SurveyResults;

@Controller
@RequestMapping("/survey-completed")
public class SurveyResultController {

	private ResultsService resultsService;
	
	private SocialApiService socialApiService;

	@Autowired
	public void setResultsService(ResultsService resultsService) {
		this.resultsService = resultsService;
	}
	
	@Autowired
	public void setSocialApiService(SocialApiService socialApiService) {
		this.socialApiService = socialApiService;
	}

	@RequestMapping(value="{myId}/{coupleId}", method=RequestMethod.GET)
	public ModelAndView getResultView(@PathVariable("myId") String myId, @PathVariable("coupleId") long coupleId) throws IOException {
		SurveyResults results = resultsService.getSurveyResults(coupleId, myId);
		
		if (results.isSurveyCompleted()) {
			ModelAndView modelAndView = new ModelAndView("survey-completed");
			
			modelAndView.addObject("me", socialApiService.getUser(results.getMyId()));
			modelAndView.addObject("partner", socialApiService.getUser(results.getPartnerId()));
			
			int percent = results.getMatchPercentage();
			modelAndView.addObject("percent", percent);
			modelAndView.addObject("judgment", getJudgment(percent));

			return modelAndView;
		} else {
			return new ModelAndView("wait-for-partner");
		}
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
//		return new String(s.getBytes(Charset.forName("windows-1251")), Charset.forName("UTF-8"));
		return s;
	}
}
