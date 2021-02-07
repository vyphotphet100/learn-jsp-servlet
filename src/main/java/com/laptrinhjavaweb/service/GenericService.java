package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.paging.Pagable;

public interface GenericService<T> {
	List<T> findAll();
	List<T> findAll(Pagable pagable);
	T findOne(Long id);
	T save(T model);
	T update(T model);
	void delete(long[] ids); 
	
	Integer getTotalItem();
	Integer getTotalPage(int nItemPerPage);
}
