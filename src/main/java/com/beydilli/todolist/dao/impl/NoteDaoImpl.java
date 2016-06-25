package com.beydilli.todolist.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beydilli.todolist.dao.NoteDao;
import com.beydilli.todolist.model.Note;
import com.beydilli.todolist.model.User;

@Repository("noteDao")
@Transactional
public class NoteDaoImpl extends BaseDaoImpl<Note> implements NoteDao {

	@Override
	public Note getNoteByNoteNameAndUser(User user, String noteName) {
		return (Note) currentSession().getNamedQuery("Note.getNoteByNoteNameAndUser").setParameter("user", user)
				.setParameter("noteName", noteName).uniqueResult();
	}

}
