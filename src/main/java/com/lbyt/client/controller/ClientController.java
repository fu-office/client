package com.lbyt.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lbyt.client.bean.CustomerImportJsonBean;
import com.lbyt.client.bean.JsonBean;

@Controller
@RequestMapping("/customer")
public class ClientController {

	@RequestMapping(value="/import.json", method=RequestMethod.POST)
	@ResponseBody
	public JsonBean importCustomers(final CustomerImportJsonBean fileBean){
		JsonBean jsonBean = new JsonBean();
		fileBean.getFile();
		return jsonBean;
	}
}
