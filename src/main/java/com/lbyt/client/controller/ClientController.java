package com.lbyt.client.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lbyt.client.bean.ClientSearchBean;
import com.lbyt.client.bean.CustomerImportJsonBean;
import com.lbyt.client.service.ClientService;

@Controller
@RequestMapping("/customer")
public class ClientController {

	@Autowired
	private ClientService clientService; 
	
	@RequestMapping(value="/import.json", method=RequestMethod.POST)
	@ResponseBody
	public ClientSearchBean importCustomers(final CustomerImportJsonBean fileBean) throws IOException{
		return clientService.importExcel(fileBean.getFile());
	}
}
