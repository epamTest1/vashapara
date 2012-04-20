package com.couple.services;

import java.util.List;

import com.couple.model.Result;
import com.couple.web.dto.SurveyAnswers;

public interface ResultsService {

	long saveAnswers(SurveyAnswers answers);

	List<Result> getResults(boolean positive);
}
