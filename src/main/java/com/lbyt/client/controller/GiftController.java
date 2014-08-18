package com.lbyt.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lbyt.client.bean.GiftBean;
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
}
