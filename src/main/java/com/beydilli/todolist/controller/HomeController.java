package com.beydilli.todolist.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.beydilli.todolist.dao.NoteDao;
import com.beydilli.todolist.dao.UserDao;
import com.beydilli.todolist.domain.RestfulResult;
import com.beydilli.todolist.domain.SessionKey;
import com.beydilli.todolist.domain.Status;
import com.beydilli.todolist.model.Note;
import com.beydilli.todolist.model.User;
import com.beydilli.todolist.util.WebUtil;

@Controller
@RequestMapping(value = "/home")
public class HomeController {

	@Autowired
	private UserDao userDao;

	@Autowired
	private NoteDao noteDao;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request) {
		if (WebUtil.isUserLoggedIn(request)) {
			Map<String, Object> model = new HashMap<String, Object>();
			model.put("user", (User) WebUtil.getFromSession(request, SessionKey.USER));
			return new ModelAndView("views/home", model);
		} else {
			return new ModelAndView("redirect:login");
		}
	}

	@RequestMapping(value = "/addNote", method = RequestMethod.POST)
	@ResponseBody
	public RestfulResult addNote(HttpServletRequest request, String noteName) {
		if (WebUtil.isUserLoggedIn(request)) {
			Long userId = (Long) WebUtil.getFromSession(request, SessionKey.USER_ID);
			User user = userDao.findById(userId);
			Note note = new Note(noteName, user, false);
			user.getNotes().add(note);
			user = userDao.save(user);
			WebUtil.addToSession(request, user, SessionKey.USER);
			return new RestfulResult(Status.SUCCES, note.getId().toString());
		} else {
			return new RestfulResult(Status.FAILED, "Lüften giriş yapınız.");
		}
	}

	@RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
	@ResponseBody
	public RestfulResult updateStatus(HttpServletRequest request, Long noteId, int status) {
		if (WebUtil.isUserLoggedIn(request)) {
			Long userId = (Long) WebUtil.getFromSession(request, SessionKey.USER_ID);
			Note note = noteDao.getNoteByNoteId(noteId);
			if (status == 1) {
				note.setStatus(true);
			} else {
				note.setStatus(false);
			}
			noteDao.save(note);
			User user = userDao.findById(userId);
			WebUtil.addToSession(request, user, SessionKey.USER);
			return new RestfulResult(Status.SUCCES, "Başarı ile güncellendi.");
		} else {
			return new RestfulResult(Status.FAILED, "Lüften giriş yapınız.");
		}
	}
	
	@RequestMapping(value = "/removeNote", method = RequestMethod.POST)
	@ResponseBody
	public RestfulResult removeNote(HttpServletRequest request, Long noteId) {
		if (WebUtil.isUserLoggedIn(request)) {
			Long userId = (Long) WebUtil.getFromSession(request, SessionKey.USER_ID);
			noteDao.deleteById(noteId);
			User user = userDao.findById(userId);
			WebUtil.addToSession(request, user, SessionKey.USER);
			return new RestfulResult(Status.SUCCES, "Başarı ile silindi.");
		} else {
			return new RestfulResult(Status.FAILED, "Lüften giriş yapınız.");
		}
	}
}
