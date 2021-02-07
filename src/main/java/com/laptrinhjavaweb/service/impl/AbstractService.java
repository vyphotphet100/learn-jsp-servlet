package com.laptrinhjavaweb.service.impl;

import com.laptrinhjavaweb.service.GenericService;

public abstract class AbstractService<T> implements GenericService<T> {
	
	@Override
	public Integer getTotalPage(int nItemPerPage) {
		int totalItem = this.getTotalItem();
		int tmp = totalItem / nItemPerPage;
		if (totalItem % nItemPerPage == 0)
			return tmp;
		else 
			return (tmp + 1);
	}
}
