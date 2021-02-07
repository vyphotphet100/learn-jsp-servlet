package com.laptrinhjavaweb.paging;

import com.laptrinhjavaweb.sort.Sorter;

public interface Pagable {
	Integer getOffset();
	Integer getLimit();
	Sorter getSorter();
}
