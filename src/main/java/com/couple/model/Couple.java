package com.couple.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Couple {
	private Long id;
	private Collection<String> partnerIds;
	private Integer score;
	
	private Map<String, Map<Long, AnswerOption>> answers = new HashMap<String, Map<Long,AnswerOption>>();
	
	protected Couple() {
	}
	
	public Couple(String firstPartnerId, String secondPartnerId) {
		partnerIds = Arrays.asList(firstPartnerId, secondPartnerId);
		answers.put(firstPartnerId, new HashMap<Long, AnswerOption>());
		answers.put(secondPartnerId, new HashMap<Long, AnswerOption>());
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
		answers.get(partnerId).put(questionId, answer);
	}

	public void calculateScore() {
		Iterator<String> it = answers.keySet().iterator();
		Map<Long, AnswerOption> answers1 = answers.get(it.next());
		Map<Long, AnswerOption> answers2 = answers.get(it.next());
		
		if (!answers1.isEmpty() || !answers2.isEmpty()) {
			int sum = 0;
			
			Collection<Long> commonKeys = new ArrayList<Long>(answers1.keySet());
			commonKeys.retainAll(answers2.keySet());
			for(Long questionId: commonKeys) {
				sum += 100 - Math.abs(answers1.get(questionId).getWeight() + answers2.get(questionId).getWeight() - 100);
			}
			if (commonKeys.size() > 0) {
				this.score = sum / commonKeys.size();
			} else {
				this.score = 0;
			}
		}
		
	}
}
