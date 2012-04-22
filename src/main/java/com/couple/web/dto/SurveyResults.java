package com.couple.web.dto;


public class SurveyResults {
	private int matchPercentage = -1;
	private String myId;
	private String partnerId;
	private boolean surveyCompleted;
	
	public SurveyResults(int matchPercentage) {
		this.matchPercentage = matchPercentage;
		this.surveyCompleted = true;
	}

	public SurveyResults() {
	}

	public boolean isSurveyCompleted() {
		return surveyCompleted;
	}

	public int getMatchPercentage() {
		return matchPercentage;
	}

	public String getMyId() {
		return myId;
	}
	
	public void setMyId(String myId) {
		this.myId = myId;
	}

	public String getPartnerId() {
		return partnerId;
	}
	
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
}
