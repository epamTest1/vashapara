package com.couple.controllers;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VKAPITest {
    @RequestMapping(value = "/vk", method = GET)
    public @ResponseBody
    String generateRecaptcha() {
		return "<h1>Test</h1>";
    }
}
