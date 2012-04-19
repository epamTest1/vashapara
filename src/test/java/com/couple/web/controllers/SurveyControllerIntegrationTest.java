package com.couple.web.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;

import com.couple.model.Answer;
import com.couple.model.Category;
import com.couple.services.CategoryService;
import com.couple.services.ResultsService;
import com.couple.web.dto.SurveyAnswers;
import com.couple.web.dto.User;

public class SurveyControllerIntegrationTest {
	private static final String MY_ID = "1";
	private static final String PARTNER_ID = "2";
	
	private CategoryService categoryService = mock(CategoryService.class);
	private ResultsService resultsService = mock(ResultsService.class);
	private ChoosePartner choosePartner = mock(ChoosePartner.class);
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		SurveyController controller = new SurveyController();
		controller.setCategoryService(categoryService);
		controller.setResultsService(resultsService);
		controller.setChoosePartner(choosePartner);
		
		UrlBasedViewResolver resolver = new UrlBasedViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/");
		resolver.setSuffix(".jsp");
		
		mockMvc = MockMvcBuilders.standaloneSetup(controller).setViewResolvers(resolver).build();
	}
	
	@Test
	public void shouldFillModelForSurveyForm() throws Exception {
		List<Category> categories = Arrays.asList(new Category("dummy"));
		User me = new User(MY_ID, "dummy", "");
		User partner = new User(PARTNER_ID, "dummy", "");
		
		when(categoryService.getCategories()).thenReturn(categories);
		when(choosePartner.getUser(MY_ID)).thenReturn(me);
		when(choosePartner.getUser(PARTNER_ID)).thenReturn(partner);
		
		mockMvc.perform(get("/survey").param("myId", MY_ID).param("partnerId", PARTNER_ID))
			.andExpect(model().attribute("categories", categories))
			.andExpect(model().attribute("me", me))
			.andExpect(model().attribute("partner", partner));
	}
	
	@Test
	public void shouldRenderSurveyForm() throws Exception {
		mockMvc.perform(get("/survey").param("myId", MY_ID).param("partnerId", PARTNER_ID))
			.andExpect(view().name("survey"));
	}
	
	@Test
	public void shouldPassResultsToService() throws Exception {
		mockMvc.perform(post("/survey/{myId}/{partnerId}", MY_ID, PARTNER_ID)
				.param("question-1", "NEVER")
				.param("question-2", "EQUALY")
				.param("question-3", "OFTEN"));
		
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
	public void shouldRedirectToResultsPage() throws Exception {
		long coupleId = 1;
		when(resultsService.saveAnswers(any(SurveyAnswers.class))).thenReturn(coupleId);
		
		mockMvc.perform(post("/survey/{myId}/{partnerId}", MY_ID, PARTNER_ID)
				.param("question-1", "NEVER")
				.param("question-2", "EQUALY")
				.param("question-3", "OFTEN"))
			.andExpect(redirectedUrl("/survey/" + coupleId));
	}
}
