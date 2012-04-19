package com.couple.services.impl;

import org.springframework.stereotype.Service;

import com.couple.services.ResultsService;
import com.couple.web.dto.SurveyAnswers;

@Service
public class ResultsServiceImpl implements ResultsService {

	@Override
	public long saveAnswers(SurveyAnswers answers) {
		return 0;
	}
}
