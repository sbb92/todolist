package com.beydilli.todolist.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "NOTE")
@NamedQueries({
	@NamedQuery(name = "Note.getNoteByNoteNameAndUser", query="select n from Note n where n.user=:user and n.name=noteName"),
	@NamedQuery(name = "Note.updateTextByNoteNameAndUser", query="update Note n set n.text = :text where n.user=:user and n.name=noteName")

})
public class Note extends BaseModel {

	private String name;
	private String text = "";
	private User user;

	public Note() {
		super();
	}

	public Note(String name, User user) {
		super();
		this.name = name;
		this.user = user;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonIgnore
	@Column(name = "TEXT", columnDefinition = "TEXT")
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "USER_ID", nullable = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
