package com.couple.web.dto;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.couple.model.AnswerOption;

public class SurveyAnswers {
	private final String userId;
	private final String partnerId;
	private final Map<Long, AnswerOption> answers = new HashMap<Long, AnswerOption>();
	
	public SurveyAnswers(String userId, String partnerId) {
		this.userId = userId;
		this.partnerId = partnerId;
	}

	public String getUserId() {
		return userId;
	}
	
	public String getPartnerId() {
		return partnerId;
	}

	public Map<Long, AnswerOption> getAnswers() {
		return Collections.unmodifiableMap(answers);
	}
	
	public void addAnswer(long questionId, AnswerOption answer) {
		answers.put(questionId, answer);
	}
}
