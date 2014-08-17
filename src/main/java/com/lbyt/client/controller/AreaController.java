package com.lbyt.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lbyt.client.bean.AreaBean;
import com.lbyt.client.bean.AreaImportBean;
import com.lbyt.client.bean.AreaSearchBean;
import com.lbyt.client.bean.JsonBean;
import com.lbyt.client.service.AreaService;

@Controller
@RequestMapping("/area")
public class AreaController {
	@Autowired
	private AreaService areaService;
	
	@RequestMapping(value = "/import.json", method = RequestMethod.POST)
	@ResponseBody
	public AreaSearchBean importArea(AreaImportBean importBean) {
		return areaService.importArea(importBean.getFile());
	}
	
	@RequestMapping("/export.json")
	public JsonBean export(AreaBean area) {
		return null;
	}
	
	@RequestMapping("/search.json")
	@ResponseBody
	public AreaSearchBean search(AreaSearchBean json) {
		
		return null;
	}
	
	@RequestMapping("/update.json")
	@ResponseBody
	public JsonBean update(AreaBean area) {
		
		return null;
	}
	
	@RequestMapping("/save.json")
	@ResponseBody
	public AreaBean save(AreaBean area) {
		return null;
	}
	
	@RequestMapping("/findAll.json")
	@ResponseBody
	public AreaSearchBean findAll() {
		AreaSearchBean json = new AreaSearchBean();
		json.setList(areaService.findAll());
		return json;
	}
}
