package com.couple.web.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.couple.services.ResultsService;
import com.couple.services.external.SocialApiService;
import com.couple.services.external.User;
import com.couple.web.dto.SurveyResults;

public class SurveyResultControllerTest {
	private static final long TEST_COUPLE_ID = 1;
	private static final String TEST_CURRENT_USER_ID = "1111";
	private static final User me = new User("me", "test");
	private static final User partner = new User("partner", "test");
	
	private ResultsService resultsService = mock(ResultsService.class);
	private SocialApiService socialApiService = mock(SocialApiService.class);
	
	private SurveyResults results = mock(SurveyResults.class);
	
	private SurveyResultController controller = new SurveyResultController();
	
	@Before
	public void setUp() {
		when(results.isSurveyCompleted()).thenReturn(true);
		when(results.getMatchPercentage()).thenReturn(40);
		when(results.getMyId()).thenReturn(me.getId());
		when(results.getPartnerId()).thenReturn(partner.getId());
		
		controller = new SurveyResultController();
		controller.setResultsService(resultsService);
		controller.setSocialApiService(socialApiService);
	}
	
	@Test
	public void shouldRenderResultViewIfSurveyCompleted() throws IOException {
		when(resultsService.getSurveyResults(eq(TEST_COUPLE_ID), anyString())).thenReturn(results);
		
		ModelAndView modelAndView = controller.getResultView(TEST_CURRENT_USER_ID, TEST_COUPLE_ID);
		
		assertEquals("survey-completed", modelAndView.getViewName());
	}
	
	@Test
	public void shouldFillOutModel() throws IOException {
		when(resultsService.getSurveyResults(eq(TEST_COUPLE_ID), anyString())).thenReturn(results);
		when(socialApiService.getUser(me.getId())).thenReturn(me);
		when(socialApiService.getUser(partner.getId())).thenReturn(partner);
		
		ModelAndView modelAndView = controller.getResultView(TEST_CURRENT_USER_ID, TEST_COUPLE_ID);
		
		assertEquals(results.getMatchPercentage(), modelAndView.getModel().get("percent"));
		assertEquals(me, modelAndView.getModel().get("me"));
		assertEquals(partner, modelAndView.getModel().get("partner"));
		assertTrue(modelAndView.getModel().containsKey("judgment"));
	}
	
	@Test
	public void shouldRenderInviteViewIfSurveyNotCompleted() throws IOException {
		when(results.isSurveyCompleted()).thenReturn(false);
		when(resultsService.getSurveyResults(eq(TEST_COUPLE_ID), anyString())).thenReturn(results);
		
		ModelAndView modelAndView = controller.getResultView(TEST_CURRENT_USER_ID, TEST_COUPLE_ID);
		
		assertEquals("wait-for-partner", modelAndView.getViewName());
	}
}
