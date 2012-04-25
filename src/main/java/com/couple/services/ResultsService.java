package com.couple.services;

import com.couple.web.dto.SurveyAnswers;
import com.couple.web.dto.SurveyResults;

public interface ResultsService {
	long saveAnswers(SurveyAnswers answers);

	SurveyResults getSurveyResults(long coupleId, String currentUserId);
}
