package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.model.NewModel;

public interface INewService extends GenericService<NewModel>{
	List<NewModel> findByCategoryId(Long categoryId);
}
