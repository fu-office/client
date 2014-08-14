package com.lbyt.client.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.lbyt.client.bean.JsonBean;
import com.lbyt.client.util.ExcelUtil;
import com.lbyt.client.util.ExcelUtil.Sheet;

public class ClientService {
	
	public JsonBean importExcel(){
		JsonBean jsonBean = new JsonBean();
		jsonBean.setSuccess(false);
		return jsonBean;
	} 
	
	private void parseExcel(File file) throws IOException{
		List<Sheet> sheets = ExcelUtil.parseExcel(file);
	}
}
