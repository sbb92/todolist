package com.beydilli.todolist.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.beydilli.todolist.dao.NoteDao;
import com.beydilli.todolist.model.Note;

@Repository("noteDao")
@Transactional
public class NoteDaoImpl extends BaseDaoImpl<Note> implements NoteDao {

	@Override
	public Note getNoteByNoteId(Long noteId) {
		return (Note) currentSession().getNamedQuery("Note.getNoteByNoteIdAndUser").setParameter("noteId", noteId).uniqueResult();
	}

	@Override
	public void deleteById(Long id) {
		getSessionFactory().getCurrentSession().createQuery("delete Note where id = :id").setParameter("id",id).executeUpdate();
	}
}
