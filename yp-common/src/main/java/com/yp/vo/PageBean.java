package com.yp.vo;

import java.util.Collections;
import java.util.List;

public class PageBean<T> {
	/**
	 * 总条数
	 */
	private long total;
	/**
	 * 总页数
	 */
	private long pages;
	/**
	 * 当前页
	 */
	private long current;
	/**
	 * 列表集合
	 */
	private List<T> records=Collections.emptyList();

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public long getPages() {
		return pages;
	}

	public void setPages(long pages) {
		this.pages = pages;
	}
	
	public long getCurrent() {
		return current;
	}

	public void setCurrent(long current) {
		this.current = current;
	}

	public List<T> getRecords() {
		return records;
	}

	public PageBean<T> setRecords(List<T> records) {
		this.records = records;
		return  this;
	}
	
	
}
