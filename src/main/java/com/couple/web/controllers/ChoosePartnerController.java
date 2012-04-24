package com.couple.web.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.couple.services.external.SocialApiService;
import com.couple.web.dto.User;
import com.couple.web.dto.User.Sex;

@Controller
@RequestMapping("/choose-partner")
public class ChoosePartnerController {
	private SocialApiService socialApiService;

	@Autowired
	public void setSocialApiService(SocialApiService socialApiService) {
		this.socialApiService = socialApiService;
	}
	
	private Sex oppositeSex(Sex sex) {
		switch (sex) {
			case MALE:
				return Sex.FEMALE;
			case FEMALE:
				return Sex.MALE;
			default:
				return Sex.NOT_SET;
		}
	}
	
	@RequestMapping(value = "/{myId}", method = GET)
	public ModelAndView choosePartner(@PathVariable("myId") String myId) throws IOException {
		User currentUser = socialApiService.getUser(myId);

		List<Map<String, Object>> friendsList = socialApiService.getFriends(myId, oppositeSex(currentUser.getSex()));
		
		ModelAndView res = new ModelAndView("choose-partner");
		res.addObject("myId", myId);
		res.addObject("friendsList", friendsList);

		return res;
	}
}
