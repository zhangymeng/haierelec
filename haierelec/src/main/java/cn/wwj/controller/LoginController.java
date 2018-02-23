package cn.wwj.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("login")
public class LoginController {

	
	@RequestMapping("login")
	@ResponseBody
	public Map<String,Object> login(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result", true);
		return map;
	}
	
	
	@RequestMapping("index")
	public ModelAndView homePage(){
		ModelMap model = new ModelMap();
		return new ModelAndView("homePage",model);
	}
}
