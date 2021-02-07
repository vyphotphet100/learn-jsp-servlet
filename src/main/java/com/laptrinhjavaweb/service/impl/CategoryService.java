package com.laptrinhjavaweb.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.ICategoryDAO;
import com.laptrinhjavaweb.model.CategoryModel;
import com.laptrinhjavaweb.paging.Pagable;
import com.laptrinhjavaweb.service.ICategoryService;

public class CategoryService extends AbstractService<CategoryModel> implements ICategoryService {
	
	@Inject
	private ICategoryDAO categoryDao;

	@Override
	public List<CategoryModel> findAll() {
		return categoryDao.findAll();
	}
	
	@Override
	public List<CategoryModel> findAll(Pagable pagable) {
		return categoryDao.findAll(pagable);
	}

	@Override
	public CategoryModel save(CategoryModel categoryModel) {
		return categoryDao.findOne(categoryDao.save(categoryModel));
	}

	@Override
	public CategoryModel findOne(Long id) {
		return categoryDao.findOne(id);
	} 
	
	@Override 
	public CategoryModel update(CategoryModel categoryModel) {
		CategoryModel oldModel = categoryDao.findOne(categoryModel.getId());
		categoryModel.setCreatedDate(oldModel.getCreatedDate());
		categoryModel.setCreatedBy(oldModel.getCreatedBy());
		categoryDao.update(categoryModel);
		
		return categoryDao.findOne(categoryModel.getId());
	}
	
	@Override
	public void delete(long[] ids) {
		for (int i = 0; i < ids.length; i++) {
			categoryDao.delete(ids[i]);
		}
	}
	
	@Override 
	public Integer getTotalItem() {
		return categoryDao.findAll().size();
	}
}
