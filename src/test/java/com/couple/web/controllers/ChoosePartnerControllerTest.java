package com.couple.web.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import com.couple.services.external.SocialApiService;
import com.couple.services.external.User;
import com.couple.services.external.User.Sex;

public class ChoosePartnerControllerTest {
	private static final User testUser = new User("id", "", "");
	private static final List<Map<String, Object>> testFriends = Collections.emptyList();
	
	private final SocialApiService socialApiService = mock(SocialApiService.class);
	
	private ChoosePartnerController controller;
	
	@Before
	public void setUp() throws IOException {
		when(socialApiService.getUser(testUser.getId())).thenReturn(testUser);
		when(socialApiService.getFriends(eq(testUser.getId()), any(Sex.class))).thenReturn(testFriends);
		
		controller = new ChoosePartnerController();
		controller.setSocialApiService(socialApiService);
	}
	
	@Test
	public void shouldRenderChoosePartnerForm() throws IOException {
		ModelAndView modelAndView =  controller.choosePartner(testUser.getId());
		
		assertEquals("choose-partner", modelAndView.getViewName());
	}
	
	@Test
	public void shouldSetUpModel() throws IOException {
		ModelAndView modelAndView =  controller.choosePartner(testUser.getId());
		
		assertEquals(testUser.getId(), modelAndView.getModel().get("myId"));
		assertEquals(testFriends, modelAndView.getModel().get("friendsList"));
	}
	
	@Test
	public void shouldSearchForMenForWoman() throws IOException {
		testUser.setSex(Sex.FEMALE);
		
		controller.choosePartner(testUser.getId());
		
		verify(socialApiService).getFriends(testUser.getId(), Sex.MALE);
	}
	
	@Test
	public void shouldSearchForWomenForMan() throws IOException {
		testUser.setSex(Sex.MALE);
		
		controller.choosePartner(testUser.getId());
		
		verify(socialApiService).getFriends(testUser.getId(), Sex.FEMALE);
	}
	
	@Test
	public void shouldSearchAllInNoSpecified() throws IOException {
		testUser.setSex(Sex.NOT_SET);
		
		controller.choosePartner(testUser.getId());
		
		verify(socialApiService).getFriends(testUser.getId(), Sex.NOT_SET);
	}	
}
