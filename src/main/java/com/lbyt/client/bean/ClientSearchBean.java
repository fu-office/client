package com.lbyt.client.bean;

import java.util.ArrayList;
import java.util.List;

public class ClientSearchBean extends JsonBean {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -115675320496802794L;
	
	private List<Client> list = new ArrayList<Client>();

	public List<Client> getList() {
		return list;
	}

	public void setList(List<Client> list) {
		this.list = list;
	}
	
}
