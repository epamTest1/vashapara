package com.couple.services.external;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;


@Component
public class SocialApiContext {
	@Autowired
	private HttpSession session;
	
	@Bean(name = "VkApiService")
	@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.INTERFACES)
	public SocialApiService getVkApiService() {
		return new VkApiService((String) session.getAttribute("apiUrl"));
	}
}
