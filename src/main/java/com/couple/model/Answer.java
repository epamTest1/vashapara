package com.couple.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Embeddable
class Answer {
	@Column(name = "QUESTION_ID")
	private long questionId;
	
	@Column(name = "ANSWER")
	@Enumerated(EnumType.STRING)
	private AnswerOption answer;
	
	protected Answer() {
	}
	
	public Answer(Partner partner, long questionId, AnswerOption answer) {
		this.questionId = questionId;
		this.answer = answer;
	}
	
	public long getQuestionId() {
		return questionId;
	}
	
	public AnswerOption getAnswer() {
		return answer;
	}
}
