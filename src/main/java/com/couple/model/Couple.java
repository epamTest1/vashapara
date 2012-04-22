package com.couple.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public class Couple {
	private Long id;
	private Collection<String> partnerIds;
	private Integer score;
	
	protected Couple() {
	}
	
	public Couple(String firstPartnerId, String secondPartnerId) {
		partnerIds = Arrays.asList(firstPartnerId, secondPartnerId);
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Collection<String> getPartnerIds() {
		return partnerIds;
	}

	public Integer getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}

	public Map<Long, AnswerOption> getAnswersFor(String partnerId) {
		return null;
	}

	public void setAnswer(String partnerId, long questionId, AnswerOption answer) {
		// TODO Auto-generated method stub
		
	}

	public void calculateScore() {
		// TODO Auto-generated method stub
	}
}
