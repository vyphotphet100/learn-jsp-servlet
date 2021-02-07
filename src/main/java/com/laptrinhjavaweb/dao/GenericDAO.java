package com.laptrinhjavaweb.dao;

import java.util.List;
import com.laptrinhjavaweb.mapper.RowMapper;
import com.laptrinhjavaweb.paging.Pagable;

public interface GenericDAO<T> {
	List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters);
	Long insert(String sql, Object... parameters);
	void update(String sql, Object... parameters);
	
	List<T> findAll();
	List<T> findAll(Pagable pagable);
	T findOne(Long id);
	Long save(T model);
	void update(T model);
	void delete(long id);
}
