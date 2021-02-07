package com.laptrinhjavaweb.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.IUserDAO;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.paging.Pagable;
import com.laptrinhjavaweb.service.IUserService;

public class UserService extends AbstractService<UserModel> implements IUserService{

	@Inject 
	private IUserDAO userDao;
	
	@Override
	public List<UserModel> findAll() {
		return userDao.findAll();
	}

	@Override
	public List<UserModel> findAll(Pagable pagable) {
		return userDao.findAll(pagable);
	}

	@Override
	public UserModel findOne(Long id) {
		return userDao.findOne(id);
	}

	@Override
	public UserModel save(UserModel model) {
		return userDao.findOne(userDao.save(model));
	}

	@Override
	public UserModel update(UserModel model) {
		userDao.update(model);
		return userDao.findOne(model.getId());
	}

	@Override
	public void delete(long[] ids) {
		for (int i=0; i<ids.length; i++)
			userDao.delete(ids[i]);
	}

	@Override
	public Integer getTotalItem() {
		return userDao.findAll().size();
	}

	@Override
	public UserModel findByUsernameAndPasswordAndStatus(String username, String password, Integer status) {
		return userDao.findByUsernameAndPasswordAndStatus(username, password, status);
	}

}
