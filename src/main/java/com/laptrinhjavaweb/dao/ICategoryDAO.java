package com.laptrinhjavaweb.dao;

import com.laptrinhjavaweb.model.CategoryModel;

public interface ICategoryDAO extends GenericDAO<CategoryModel>{
	CategoryModel findOneByCode(String categoryCode);
}
