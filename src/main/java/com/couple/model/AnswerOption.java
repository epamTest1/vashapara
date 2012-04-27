package com.couple.model;

public enum AnswerOption {
	NEVER(0),
	SOMETIMES(25),
	EQUALY(50),
	OFTEN(75),
	ALWAYS(100);
	
	private int weight;
	
	int getWeight() {
		return weight;
	}
	
	private AnswerOption(int weight) {
		this.weight = weight;
	}
}
