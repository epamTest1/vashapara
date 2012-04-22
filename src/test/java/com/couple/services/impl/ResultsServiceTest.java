package com.couple.services.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.couple.model.Couple;
import com.couple.persistence.CoupleDao;
import com.couple.web.dto.SurveyResults;

public class ResultsServiceTest {
	private static final String CURRENT_USER_ID = "me";
	private static final String PARTNER_ID = "partner";
	private static final long TEST_COUPLE_ID = 42;
	
	private Couple couple = mock(Couple.class);
	
	private final CoupleDao coupleDao = mock(CoupleDao.class);
	
	private ResultsServiceImpl resultsService;
	
	@Before
	public void setUp() {
		when(couple.getPartnerIds()).thenReturn(Arrays.asList(PARTNER_ID, CURRENT_USER_ID));
		when(couple.getScore()).thenReturn(42);
		
		when(coupleDao.find(TEST_COUPLE_ID)).thenReturn(couple);
		
		resultsService = new ResultsServiceImpl();
		resultsService.setCoupleDao(coupleDao);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldFailIfWrongUserPassed() {
		resultsService.getSurveyResults(TEST_COUPLE_ID, "another");
	}
	
	@Test
	public void shouldDetermineCurrentUser() {
		SurveyResults surveyResults = resultsService.getSurveyResults(TEST_COUPLE_ID, CURRENT_USER_ID);
		
		assertEquals(CURRENT_USER_ID, surveyResults.getMyId());
		assertEquals(PARTNER_ID, surveyResults.getPartnerId());
	}
	
	@Test
	public void shouldLoadResultFromDao() {
		SurveyResults surveyResults = resultsService.getSurveyResults(TEST_COUPLE_ID, CURRENT_USER_ID);
		
		assertEquals(couple.getScore().longValue(), surveyResults.getMatchPercentage());
		assertTrue(surveyResults.isSurveyCompleted());
	}
	
	@Test
	public void shouldInformAboutNotCompletedSurvey() {
		when(couple.getScore()).thenReturn(null);
		
		SurveyResults surveyResults = resultsService.getSurveyResults(TEST_COUPLE_ID, CURRENT_USER_ID);
		
		assertFalse(surveyResults.isSurveyCompleted());
	}
}
