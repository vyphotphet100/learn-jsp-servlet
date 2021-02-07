package com.laptrinhjavaweb.service;

import com.laptrinhjavaweb.model.UserModel;

public interface IUserService extends GenericService<UserModel>{
	UserModel findByUsernameAndPasswordAndStatus(String username, String password, Integer status);
}
