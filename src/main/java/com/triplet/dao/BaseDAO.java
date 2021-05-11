package com.triplet.dao;

import java.io.Serializable;

public interface BaseDAO<PK, T> {
	void delete(T entity);

	T saveOrUpdate(T entity);

	T findById(Serializable key);
	
	T find(Serializable id, boolean lock);
	
}
