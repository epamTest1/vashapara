package com.couple.web.dto;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.couple.model.AnswerOption;

public class SurveyAnswers {
	private final String userId;
	private final String partnerId;
	private final Map<Integer, AnswerOption> answers = new HashMap<Integer, AnswerOption>();
	
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

	public Map<Integer, AnswerOption> getAnswers() {
		return Collections.unmodifiableMap(answers);
	}
	
	public void addAnswer(int questionId, AnswerOption answer) {
		answers.put(questionId, answer);
	}
}
