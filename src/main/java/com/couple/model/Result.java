package com.couple.model;

public class Result {

	private static final int BORDER = 50;

	private int percent;

	private int questionId;

	private String question;

	private String description;

	public boolean isPositive() {
		return getPercent() > BORDER;
	}


	public int getPercent() {
		return percent;
	}


	public void setPercent(int percent) {
		this.percent = percent;
	}


	public String getQuestion() {
		return question;
	}


	public void setQuestion(String question) {
		this.question = question;
	}


	public String getDescription() {
		//TODO: description should be different for each question and result by this question
		description = "Some funny description about result";
		return description;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
}
