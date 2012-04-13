package com.couple.web.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import com.couple.model.Answer;
import com.couple.model.Category;
import com.couple.services.CategoryService;
import com.couple.services.ResultsService;
import com.couple.web.dto.SurveyAnswers;

public class SurveyControllerTest {
	private static final String MY_ID = "1";
	private static final String PARTNER_ID = "2";
	
	private ResultsService resultsService = mock(ResultsService.class);
	private CategoryService categoryService = mock(CategoryService.class);
	private MockHttpServletRequest request = new MockHttpServletRequest();
	
	private SurveyController controller;

	@Before
	public void setUp() {
		controller = new SurveyController();
		controller.setCategoryService(categoryService);
		controller.setResultsService(resultsService);
	}
	
	@Test
	public void shouldFillModelForSurveyForm() {
		List<Category> categories = Arrays.asList(new Category("dummy"));
		
		when(categoryService.getCategories()).thenReturn(categories);
		
		ModelAndView modelAndView = controller.getSurveyForm(MY_ID, PARTNER_ID);
		
		assertEquals(categories, modelAndView.getModel().get("categories"));
		assertTrue(modelAndView.getModel().containsKey("me"));
		assertTrue(modelAndView.getModel().containsKey("partner"));
	}
	
	@Test
	public void shouldRenderSurveyForm() {
		ModelAndView modelAndView = controller.getSurveyForm(MY_ID, PARTNER_ID);
		assertEquals("survey", modelAndView.getViewName());
	}
	
	@Test
	public void shouldPassResultsToService() {
		request.addParameter("question-1", "NEVER");
		request.addParameter("question-2", "EQUALY");
		request.addParameter("question-3", "OFTEN");
		
		controller.completeSurvey(MY_ID, PARTNER_ID, request);
		
		ArgumentCaptor<SurveyAnswers> answersCaptor = ArgumentCaptor.forClass(SurveyAnswers.class);
		
		verify(resultsService).saveAnswers(answersCaptor.capture());
		
		SurveyAnswers answers = answersCaptor.getValue();
		assertEquals(MY_ID, answers.getUserId());
		assertEquals(PARTNER_ID, answers.getPartnerId());
		
		Map<Integer, Answer> expectedAnswers = new HashMap<Integer, Answer>();
		expectedAnswers.put(1, Answer.NEVER);
		expectedAnswers.put(2, Answer.EQUALY);
		expectedAnswers.put(3, Answer.OFTEN);
		
		assertEquals(expectedAnswers, answers.getAnswers());
	}
	
	@Test
	public void shouldRedirectToResultsPage() {
		request.addParameter("question-1", "NEVER");
		request.addParameter("question-2", "EQUALY");
		request.addParameter("question-3", "OFTEN");
		
		View view = controller.completeSurvey(MY_ID, PARTNER_ID, request);
		
		assertTrue(view instanceof RedirectView);
		
		RedirectView redirectView = (RedirectView) view;
		assertEquals("/survey/{myId}/{partnerId}", redirectView.getUrl());
	}
}
