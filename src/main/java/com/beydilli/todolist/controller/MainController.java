package com.beydilli.todolist.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.beydilli.todolist.util.WebUtil;

@Controller
public class MainController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView redirect(HttpServletRequest request) {
		if (WebUtil.isUserLoggedIn(request)) {
			return new ModelAndView ("redirect:home");
		} else {
			return new ModelAndView ("redirect:login");
		}
	}

}
