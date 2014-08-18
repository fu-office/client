package com.lbyt.client.bean;

import java.util.ArrayList;
import java.util.List;

public class ClientSearchBean extends BaseSearchBean {
	
	private static final long serialVersionUID = -115675320496802794L;
	
	private List<ClientBean> list = new ArrayList<ClientBean>();

	public List<ClientBean> getList() {
		return list;
	}

	public void setList(List<ClientBean> list) {
		this.list = list;
	}
	
}
