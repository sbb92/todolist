package com.beydilli.todolist.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beydilli.todolist.model.Note;

public interface NoteDao extends JpaRepository<Note, Long> {

	public Note findById(Long id);

	public void deleteById(Long id);
}
