package com.lbyt.client.bean;

public class BaseSearchBean extends JsonBean {

	private static final long serialVersionUID = 2681443461936932034L;

	private int pageSize;
	
	private int pageNumer;
	
	private int count;

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNumer() {
		return pageNumer;
	}

	public void setPageNumer(int pageNumer) {
		this.pageNumer = pageNumer;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
