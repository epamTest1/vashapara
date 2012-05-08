package com.couple.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;


@Entity
@Table(name = "PARTNERS")
class Partner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "COUPLE_ID")
	private Couple couple;
	
	@Column(name = "USER_ID")
	private String userId;
	
	@ElementCollection
	@CollectionTable(name = "ANSWERS", joinColumns = @JoinColumn(name = "PARTNER_ID"))
	@MapKeyColumn(name = "questionId", insertable = false, updatable = false)
	private Map<Long, Answer> answers = new HashMap<Long, Answer>();
	
	protected Partner() {
	}
	
	public Partner(Couple couple, String userId) {
		this.couple = couple;
		this.userId = userId;
	}
	
	public Long getId() {
		return id;
	}
	
	public Couple getCouple() {
		return couple;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setAnswer(long questionId, AnswerOption answer) {
		answers.put(questionId, new Answer(this, questionId, answer));
	}
	
	public AnswerOption getAnswerFor(long questionId) {
		return answers.get(questionId).getAnswer();
	}
	
	public Set<Long> getAnsweredQuestions() {
		return Collections.unmodifiableSet(answers.keySet());
	}
	
	public boolean hasAnswers() {
		return !answers.isEmpty();
	}
}
