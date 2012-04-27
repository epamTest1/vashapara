package com.couple.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class ScoreCalculationTest {
	private static final String FIRST_PARTNER_ID = "p1";
	private static final String SECOND_PARTNER_ID = "p2";
	
	private AnswerOption firstAnswer;
	private AnswerOption secondAnswer;
	private int expectedScore;
	
	@Parameters
	public static Collection<Object[]> configs() {
		return Arrays.<Object[]>asList(
				new Object[] {AnswerOption.NEVER, AnswerOption.NEVER, 0},
				new Object[] {AnswerOption.EQUALY, AnswerOption.EQUALY, 100},
				new Object[] {AnswerOption.NEVER, AnswerOption.ALWAYS, 100},
				new Object[] {AnswerOption.SOMETIMES, AnswerOption.OFTEN, 100},
				new Object[] {AnswerOption.ALWAYS, AnswerOption.OFTEN, 25},
				new Object[] {AnswerOption.OFTEN, AnswerOption.NEVER, 75},
				new Object[] {AnswerOption.SOMETIMES, AnswerOption.SOMETIMES, 50}
		);
	}
	
	public ScoreCalculationTest(AnswerOption firstAnswer, AnswerOption secondAnswer, int expectedScore) {
		this.firstAnswer = firstAnswer;
		this.secondAnswer = secondAnswer;
		this.expectedScore = expectedScore;
	}
	
	@Test
	public void shouldReturnCalculateScore() {
		Couple couple = new Couple(FIRST_PARTNER_ID, SECOND_PARTNER_ID);
		couple.setAnswer(FIRST_PARTNER_ID, 1, firstAnswer);
		couple.setAnswer(SECOND_PARTNER_ID, 1, secondAnswer);
		
		couple.calculateScore();
		
		assertNotNull(couple.getScore());
		assertEquals(expectedScore, couple.getScore().intValue());
	}
}
