package com.beydilli.todolist.dao;

import com.beydilli.todolist.model.Note;

public interface NoteDao extends BaseDao<Note>{
	public Note getNoteByNoteId( Long noteId);
}
