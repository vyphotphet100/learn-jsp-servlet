package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import com.laptrinhjavaweb.dao.ICategoryDAO;
import com.laptrinhjavaweb.mapper.CategoryMapper;
import com.laptrinhjavaweb.model.CategoryModel;
import com.laptrinhjavaweb.paging.Pagable;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO {

	@Override
	public List<CategoryModel> findAll() {
		String sql = "SELECT * FROM category;";
		return this.query(sql, new CategoryMapper());
	}
	
	@Override
	public List<CategoryModel> findAll(Pagable pagable) {
		StringBuilder sql = new StringBuilder("SELECT * FROM category ");
		
		if (pagable.getSorter().getSortName() != null && pagable.getSorter().getSortBy() != null) 
			sql.append("ORDER BY " + pagable.getSorter().getSortName() + " " + pagable.getSorter().getSortBy() + " ");
		
		if (pagable.getOffset() != null && pagable.getLimit() != null) 
			sql.append("LIMIT "+ pagable.getLimit() +" OFFSET "+ pagable.getOffset());
		
		return this.query(sql.toString(), new CategoryMapper());
	}

	@Override
	public Long save(CategoryModel categoryModel) {
		String sql = "INSERT INTO category(name, code) VALUES (?, ?);";
		return this.insert(sql, categoryModel.getName(), categoryModel.getCode());
	}

	@Override
	public CategoryModel findOne(Long id) {
		String sql = "SELECT * FROM category WHERE id = ?;";
		List<CategoryModel> categories = this.query(sql, new CategoryMapper(), id);
		if (!categories.isEmpty())
			return categories.get(0);
		return null;
	}

	@Override
	public void update(CategoryModel categoryModel) {
		StringBuilder sql = new StringBuilder("UPDATE category SET name = ?, code = ?, ");
		sql.append("modifieddate = ?, modifiedby = ? ");
		sql.append("WHERE id = ?;");
		this.update(sql.toString(), categoryModel.getName(), categoryModel.getCode(), categoryModel.getModifiedDate(),
				categoryModel.getModifiedBy());
	}
	
	@Override
	public void delete(long id) {
		String sql = "DELETE FROM category WHERE id = ?";
		this.update(sql, id);
	}

	@Override
	public CategoryModel findOneByCode(String categoryCode) {
		String sql = "SELECT * FROM category WHERE code = ?";
		List<CategoryModel> lRes = this.query(sql, new CategoryMapper(), categoryCode);
		
		if (!lRes.isEmpty()) 
			return lRes.get(0);
		return null;
	}
}
