package com.beydilli.todolist.dao;

public interface BaseDao<T> {
	public T save(T obj);
	public void delete(T obj);
	public T findById(Long id);
	public T update(T obj);
}
