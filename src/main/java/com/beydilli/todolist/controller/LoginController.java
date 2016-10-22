package com.beydilli.todolist.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.beydilli.todolist.domain.RestfulResult;
import com.beydilli.todolist.form.UserForm;
import com.beydilli.todolist.service.UserService;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	@ResponseBody
	public RestfulResult signUp(HttpServletRequest request, UserForm userForm) {
		return userService.signUp(request, userForm);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public RestfulResult signIn(HttpServletRequest request, String email, String password) {
		return userService.login(request, email, password);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public RestfulResult signIn(HttpServletRequest request) {
		return userService.logout(request);
	}
}
