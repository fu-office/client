package com.lbyt.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lbyt.client.bean.ExcelOutputJsonBean;
import com.lbyt.client.bean.GiftBean;
import com.lbyt.client.bean.GiftImportBean;
import com.lbyt.client.bean.GiftSearchBean;
import com.lbyt.client.bean.JsonBean;
import com.lbyt.client.service.GiftService;

@Controller
@RequestMapping("/gift")
public class GiftController {
	
	@Autowired
	private GiftService giftService;
	
	@RequestMapping("/save.json")
	@ResponseBody
	public GiftBean save(@RequestBody GiftBean bean) {
		return giftService.save(bean);
	}
	
	@RequestMapping("/search.json")
	@ResponseBody
	public GiftSearchBean search(@RequestBody GiftSearchBean bean) {
		return giftService.search(bean);
	}
	
	@RequestMapping("/import.json")
	@ResponseBody
	public GiftSearchBean importExcel(GiftImportBean bean) {
		return giftService.importExcel(bean.getFile());
	}
	
	@RequestMapping("/export.json")
	@ResponseBody
	public ExcelOutputJsonBean exportExcel(@RequestBody GiftSearchBean bean){
		return null;
	}
	
	@RequestMapping("/delete.json")
	@ResponseBody
	public JsonBean delete(@RequestBody GiftBean gift){
		return giftService.delete(gift);
	}
}
