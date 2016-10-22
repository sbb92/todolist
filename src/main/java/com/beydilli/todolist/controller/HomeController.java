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
import com.beydilli.todolist.service.NoteService;

@Controller
@RequestMapping(value = "/home")
public class HomeController {

	@Autowired
	private UserDao userDao;

	@Autowired
	private NoteService noteService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request) {
		return noteService.home(request);
	}

	@RequestMapping(value = "/addNote", method = RequestMethod.POST)
	@ResponseBody
	public RestfulResult addNote(HttpServletRequest request, String noteName) {
		return noteService.addNote(request, noteName);
	}

	@RequestMapping(value = "/updateStatus", method = RequestMethod.POST)
	@ResponseBody
	public RestfulResult updateStatus(HttpServletRequest request, Long noteId, int status) {
		return noteService.updateStatus(request, noteId, status);
	}

	@RequestMapping(value = "/removeNote", method = RequestMethod.POST)
	@ResponseBody
	public RestfulResult removeNote(HttpServletRequest request, Long noteId) {
		return noteService.removeNote(request, noteId);
	}
}
