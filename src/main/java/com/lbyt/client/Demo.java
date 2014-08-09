package com.lbyt.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class Demo {
	@RequestMapping(value="/welcome.json", method=RequestMethod.GET)
	@ResponseBody
	public String doGet(){
		return "hello";
	}
}
