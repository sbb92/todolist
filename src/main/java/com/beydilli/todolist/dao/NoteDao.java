package com.beydilli.todolist.dao;

import com.beydilli.todolist.model.Note;
import com.beydilli.todolist.model.User;

public interface NoteDao extends BaseDao<Note>{
	public Note getNoteByNoteNameAndUser(User user, String noteName);
}
