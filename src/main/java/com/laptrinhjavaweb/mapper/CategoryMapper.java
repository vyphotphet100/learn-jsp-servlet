package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.CategoryModel;

public class CategoryMapper implements RowMapper<CategoryModel> {
	
	@Override
	public CategoryModel mapRow(ResultSet rs) {
		CategoryModel category = new CategoryModel();
		try {
			category.setId(rs.getLong("id"));
			category.setName(rs.getNString("name"));
			category.setCode(rs.getNString("code"));
			category.setCreatedBy(rs.getNString("createdby"));
			category.setCreatedDate(rs.getTimestamp("createddate"));
			category.setModifiedBy(rs.getNString("modifiedby"));
			category.setModifiedDate(rs.getTimestamp("modifieddate"));
			
			return category;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}

} 
