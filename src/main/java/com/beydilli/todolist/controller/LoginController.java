package com.beydilli.todolist.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.beydilli.todolist.dao.UserDao;
import com.beydilli.todolist.domain.RestfulResult;
import com.beydilli.todolist.domain.SessionKey;
import com.beydilli.todolist.domain.Status;
import com.beydilli.todolist.model.User;
import com.beydilli.todolist.util.UserCredentialsValidator;
import com.beydilli.todolist.util.WebUtil;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request) {
		if (WebUtil.isUserLoggedIn(request)) {
			return new ModelAndView("redirect:home");
		} else {
			return new ModelAndView("login");
		}
	}

	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	@ResponseBody
	public RestfulResult signUp(HttpServletRequest request, String email, String password, String name,
			String surname) {
		if (!UserCredentialsValidator.validateUserCredentials(name, surname, email, password)) {
			return new RestfulResult(Status.FAILED, "Kullanıcı bilgilerini lütfen doğru giriniz.");
		}
		User user = new User(name, surname, email, password);
		userDao.save(user);
		WebUtil.addToSession(request, user.getId(), SessionKey.USER_ID);
		WebUtil.addToSession(request, user, SessionKey.USER);
		return new RestfulResult(Status.SUCCES, "Kullanıcınız başarı ile oluşturulmuştur.");

	}

	@RequestMapping(value = "/signIn", method = RequestMethod.POST)
	@ResponseBody
	public RestfulResult signIn(HttpServletRequest request, String email, String password) {

		if (!UserCredentialsValidator.validateUserCredentials(email, password)) {
			return new RestfulResult(Status.FAILED, "Kullanıcı bilgilerini lütfen doğru giriniz.");
		}
		User user = userDao.findByEmailAndPassword(email, password);
		if (user == null) {
			return new RestfulResult(Status.FAILED, "Kullanıcı bilgilerini lütfen doğru giriniz.");
		}
		WebUtil.addToSession(request, user.getId(), SessionKey.USER_ID);
		WebUtil.addToSession(request, user, SessionKey.USER);
		return new RestfulResult(Status.SUCCES, "Başarı ile giriş yaptınız.");

	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	@ResponseBody
	public RestfulResult signIn(HttpServletRequest request) {
		WebUtil.removeFromSession(request, SessionKey.USER_ID);
		WebUtil.removeFromSession(request, SessionKey.USER);
		return new RestfulResult(Status.SUCCES, "Başarı ile çıkış yaptınız.");

	}
}
