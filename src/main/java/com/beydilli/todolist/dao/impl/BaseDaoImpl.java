package com.beydilli.todolist.dao.impl;

import java.lang.reflect.ParameterizedType;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.beydilli.todolist.dao.BaseDao;

@Transactional
public class BaseDaoImpl <T> extends HibernateDaoSupport implements BaseDao<T>{

	private Class<T> entityClass;
	
	@Autowired
    public void setSession(SessionFactory sessionFactory){
        this.setSessionFactory(sessionFactory);
    }
	
	@SuppressWarnings("unchecked")
	public BaseDaoImpl() {
		entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public T save(T obj) {
		getHibernateTemplate().saveOrUpdate(obj);
		return obj;
	}

	@Override
	public void delete(T obj) {
		getHibernateTemplate().delete(obj);
	}

	@Override
	public T findById(Long id) {
		return (T) getHibernateTemplate().get(entityClass, id);
	}

	@Override
	public T update(T obj) {
		return getHibernateTemplate().merge(obj);
	}

	@Override
	public void deleteById(Long id) {
		
	}

}
