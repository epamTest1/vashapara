package com.couple.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.couple.model.Result;
import com.couple.services.ResultsService;
import com.couple.web.dto.SurveyAnswers;

@Service
public class ResultsServiceImpl implements ResultsService {

	@Override
	public long saveAnswers(SurveyAnswers answers) {
		return 0;
	}

	@Override
	public List<Result> getResults(boolean positive) {
		List<Result> result = new ArrayList<Result>();


		//TODO: load answers and other info for output
		for (int i=0; i<10; i++) {
			Result q = new Result();
			q.setPercent(45+i);
			q.setQuestion("Вопрос, Question about smth number " + i);

			if (positive && q.isPositive()) {
				result.add(q);
			} else if (!positive && !q.isPositive()){
				result.add(q);
			}
		}
		//*

		return result;
	}
}
