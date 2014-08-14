package com.lbyt.client.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lbyt.client.bean.JsonBean;

@Controller
@RequestMapping("/customer")
public class ClientController {

	@RequestMapping("/import")
	@ResponseBody
	public JsonBean importCustomers(JsonBean fileBean){
		JsonBean jsonBean = new JsonBean();
		return jsonBean;
	}
}
