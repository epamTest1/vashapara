package com.couple.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Test;


public class CoupleTest {
	private static final String FIRST_PARTNER_ID = "p1";
	private static final String SECOND_PARTNER_ID = "p2";
	
	@Test
	public void shouldReturnNullForScoreIfDataNotSufficient() {
		Couple couple = new Couple(FIRST_PARTNER_ID, SECOND_PARTNER_ID);
		
		couple.calculateScore();
		
		assertNull(couple.getScore());
	}
	
	@Test
	public void shouldCalctulateIfAllDataHere() {
		Couple couple = new Couple(FIRST_PARTNER_ID, SECOND_PARTNER_ID);
		couple.setAnswer(FIRST_PARTNER_ID, 1, AnswerOption.NEVER);
		couple.setAnswer(SECOND_PARTNER_ID, 1, AnswerOption.NEVER);
		
		couple.calculateScore();
		
		assertNotNull(couple.getScore());
	}
	
	@Test
	public void shouldReturnZeroIfNoMatchingAnswers() {
		Couple couple = new Couple(FIRST_PARTNER_ID, SECOND_PARTNER_ID);
		couple.setAnswer(FIRST_PARTNER_ID, 1, AnswerOption.NEVER);
		couple.setAnswer(SECOND_PARTNER_ID, 2, AnswerOption.NEVER);
		
		couple.calculateScore();
		
		assertEquals(Integer.valueOf(0), couple.getScore());
	}
	
	@Test
	public void shouldSumResultsForAllQuestions() {
		Couple couple = new Couple(FIRST_PARTNER_ID, SECOND_PARTNER_ID);
		couple.setAnswer(FIRST_PARTNER_ID, 1, AnswerOption.ALWAYS);
		couple.setAnswer(SECOND_PARTNER_ID, 1, AnswerOption.NEVER);
		couple.setAnswer(FIRST_PARTNER_ID, 2, AnswerOption.EQUALY);
		couple.setAnswer(SECOND_PARTNER_ID, 2, AnswerOption.EQUALY);
		
		couple.calculateScore();
		
		assertEquals(Integer.valueOf(100), couple.getScore());
	}
}
