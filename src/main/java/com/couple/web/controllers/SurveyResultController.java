package com.couple.web.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
			in = "Вы совершенно не понимаете друг друга. Но противоположности притягиваются, не правда ли ?";
		} else if (percent < 30) {
			in = "Уровень взаимопонимания между вами крайне низок. Возможно, кто то из вас очень эгоистичен.";
		} else if (percent < 45) {
			in = "Каждый из Вас считает что именно он делает все, а партнер лентяй. Нехватка откровенности ?";
		} else if (percent < 60) {
			in = "Вы типичная современная пара. Обходите острые углы, ловя счастливые моменты.";
		} else if (percent < 75) {
			in = "Ваши отношения просто отличные. Похоже, что Вы отлично знаете друг друга.";
		} else if (percent < 90) {
			in = "Крайне крепкая пара. Просто молодцы.";
		} else {
			in = "Уникально. Такое взаимопонимание крайне редко встречается. Похоже, вы нашли свою половинку.";
		}
		return decodeGetParameter(in);
	}

	public static String decodeGetParameter(String parameter) throws UnsupportedEncodingException {
		return new String(parameter.getBytes("windows-1251"),"UTF8");
	}
}
