package com.couple.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "COUPLES")
public class Couple {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "SCORE")
	private Integer score;
	
	@OneToMany(mappedBy = "couple", cascade = CascadeType.PERSIST)
	@MapKey(name = "userId")
	private Map<String, Partner> partners = new HashMap<String, Partner>();
	
	protected Couple() {
	}
	
	public Couple(String firstPartnerId, String secondPartnerId) {
		partners.put(firstPartnerId, new Partner(this, firstPartnerId));
		partners.put(secondPartnerId, new Partner(this, secondPartnerId));
		if (partners.size() != 2) {
			throw new IllegalArgumentException("IDs of partners can not be the same");
		}
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public Collection<String> getPartnerIds() {
		return Collections.unmodifiableCollection(partners.keySet());
	}

	public Integer getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public boolean hasAnswersFor(String partnerId) {
		return partners.get(partnerId).hasAnswers();
	}
	
	public void setAnswer(String partnerId, long questionId, AnswerOption answer) {
		partners.get(partnerId).setAnswer(questionId, answer);
	}
	
	public AnswerOption getAnswer(String partnerId, long questionId) {
		return partners.get(partnerId).getAnswerFor(questionId);
	}

	public void calculateScore() {
		Iterator<Partner> iter = partners.values().iterator();
		Partner partner1 = iter.next();
		Partner partner2 = iter.next();
		
		if (partner1.hasAnswers() && partner2.hasAnswers()) {
			int sum = 0;
			
			Collection<Long> commonKeys = new ArrayList<Long>(partner1.getAnsweredQuestions());
			commonKeys.retainAll(partner2.getAnsweredQuestions());
			for(Long questionId: commonKeys) {
				sum += 100 - Math.abs(partner1.getAnswerFor(questionId).getWeight() + partner2.getAnswerFor(questionId).getWeight() - 100);
			}
			if (commonKeys.size() > 0) {
				this.score = sum / commonKeys.size();
			} else {
				this.score = 0;
			}
		}
		
	}
}
