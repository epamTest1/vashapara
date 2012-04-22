package com.couple.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public class Couple {
	private Collection<String> partnerIds;
	private Integer score;
	
	protected Couple() {
	}
	
	public Couple(String firstPartnerId, String secondPartnerId) {
		partnerIds = Arrays.asList(firstPartnerId, secondPartnerId);
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
}
