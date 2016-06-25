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
	@NamedQuery(name = "Note.getNoteByNoteIdAndUser", query = "select n from Note n where n.id=:noteId")
})
public class Note extends BaseModel {

	private String name;
	private Boolean status;
	private User user;

	public Note() {
		super();
	}

	public Note(String name, User user, boolean status) {
		super();
		this.name = name;
		this.user = user;
		this.status = status;
	}

	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Column(name = "STATUS")
	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
