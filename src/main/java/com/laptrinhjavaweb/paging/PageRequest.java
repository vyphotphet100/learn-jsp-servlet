package com.laptrinhjavaweb.paging;

import com.laptrinhjavaweb.sort.Sorter;

public class PageRequest implements Pagable {
	
	private Integer offset;
	private Integer limit;
	private Sorter sorter;
	
	
	public PageRequest(Integer page, Integer limit, Sorter sorter) {
		this.offset = (page - 1) * limit;
		this.limit = limit;
		this.sorter = sorter;
	}

	@Override
	public Integer getOffset() {
		return this.offset;
	}

	@Override
	public Integer getLimit() {
		return this.limit;
	}

	@Override
	public Sorter getSorter() {
		return this.sorter;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public void setSorter(Sorter sorter) {
		this.sorter = sorter;
	}
	
	
}
