package com.couple.web.dto;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.couple.model.Answer;

public class SurveyAnswers {
	private final String userId;
	private final String partnerId;
	private final Map<Integer, Answer> answers = new HashMap<Integer, Answer>();
	
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

	public Map<Integer, Answer> getAnswers() {
		return Collections.unmodifiableMap(answers);
	}
	
	public void addAnswer(int questionId, Answer answer) {
		answers.put(questionId, answer);
	}
}
