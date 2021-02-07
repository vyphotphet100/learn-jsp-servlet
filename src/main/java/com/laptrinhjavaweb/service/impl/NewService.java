package com.laptrinhjavaweb.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.ICategoryDAO;
import com.laptrinhjavaweb.dao.INewDAO;
import com.laptrinhjavaweb.model.CategoryModel;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.paging.Pagable;
import com.laptrinhjavaweb.service.INewService;

public class NewService extends AbstractService<NewModel> implements INewService {

	@Inject
	private INewDAO newDao;

	@Inject
	private ICategoryDAO categoryDao;

	@Override
	public List<NewModel> findAll() {
		return newDao.findAll();
	}

	@Override
	public List<NewModel> findAll(Pagable pagable) {
		return newDao.findAll(pagable);
	}

	@Override
	public List<NewModel> findByCategoryId(Long categoryId) {
		return newDao.findByCategoryId(categoryId);
	}

	@Override
	public NewModel save(NewModel newModel) {
		if (newModel.getCategoryId() == null) {
			if (newModel.getCategoryCode() == null)
				return null;
			else if (newModel.getCategoryCode() != null) {
				CategoryModel categoryModel = categoryDao.findOneByCode(newModel.getCategoryCode());
				newModel.setCategoryId(categoryModel.getId());
			}
		} 
		
		Long id = newDao.save(newModel);
		return newDao.findOne(id);
	}

	@Override
	public NewModel findOne(Long id) {
		return newDao.findOne(id);
	}

	@Override
	public NewModel update(NewModel newModel) {
		if (newModel.getCategoryId() == null) {
			if (newModel.getCategoryCode() == null)
				return null;
			else if (newModel.getCategoryCode() != null) {
				CategoryModel categoryModel = categoryDao.findOneByCode(newModel.getCategoryCode());
				newModel.setCategoryId(categoryModel.getId());
			}
		}
		
		newModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		newModel.setModifiedBy("admin");
		newDao.update(newModel);

		return newDao.findOne(newModel.getId());
	}

	@Override
	public void delete(long[] ids) {
		for (int i = 0; i < ids.length; i++) {
			newDao.delete(ids[i]);
		}
	}

	@Override
	public Integer getTotalItem() {
		return newDao.findAll().size();
	}

}
