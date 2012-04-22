package com.couple.services;

import java.util.List;

import com.couple.model.Result;
import com.couple.web.dto.SurveyAnswers;
import com.couple.web.dto.SurveyResults;

public interface ResultsService {

	long saveAnswers(SurveyAnswers answers);

	List<Result> getResults(boolean positive);

	SurveyResults getSurveyResults(long coupleId, String currentUserId);
}
