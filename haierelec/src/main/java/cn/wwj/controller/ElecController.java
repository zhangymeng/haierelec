package cn.wwj.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.wwj.po.Elec;
import cn.wwj.po.UserInfo;
import cn.wwj.service.ElecService;
import cn.wwj.vo.IndexVo;

@Controller
@RequestMapping("elec")
public class ElecController {
	@Autowired
	private ElecService elecService;
	
	@RequestMapping("/page")
	public ModelAndView page(HttpServletRequest request,IndexVo vo){
	    ModelMap model = new ModelMap();
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(user!=null){
			model.addAttribute("userInfo", user);
		}
	    return new ModelAndView("elec", model);
	}
	
	@RequestMapping("/findAll")
	@ResponseBody
    public Map<String,Object> findAll(HttpServletRequest request,IndexVo vo){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Elec> list = elecService.findAll(vo);
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", list.size());
		map.put("data", list);
        return map;
    }
	
	@RequestMapping("/del")
	@ResponseBody
    public Map<String,Object> del(HttpServletRequest request,IndexVo vo){
		Map<String,Object> map = elecService.del(vo);
        return map;
    }
	
	@RequestMapping("/add")
	@ResponseBody
    public Map<String,Object> add(HttpServletRequest request,Elec vo){
		Map<String,Object> map = new HashMap<String,Object>();
		if(vo.getId()==null){
			map = elecService.add(vo);
		}else{
			map = elecService.edit(vo);
		}
        return map;
    }
}
