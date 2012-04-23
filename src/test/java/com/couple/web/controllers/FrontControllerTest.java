package com.couple.web.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import javax.servlet.http.HttpSession;

import org.junit.Test;

public class FrontControllerTest {
	private final HttpSession session = mock(HttpSession.class);
	
	@Test
	public void shouldSetApiUrl() {
		FrontController controller = new FrontController();
		
		controller.enterApplication(session, "api_url", "viewer_id");
		
		verify(session).setAttribute("apiUrl", "api_url");
	}
	
	@Test
	public void shouldRedirectToFirstStep() {
		FrontController controller = new FrontController();
		
		String viewName = controller.enterApplication(session, "api_url", "viewer_id");
		
		assertEquals("redirect:choose-partner/viewer_id", viewName);
	}
	
}
