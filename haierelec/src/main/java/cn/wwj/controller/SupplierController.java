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
import cn.wwj.po.Supplier;
import cn.wwj.po.UserInfo;
import cn.wwj.service.SupplierService;
import cn.wwj.vo.IndexVo;

@Controller
@RequestMapping("supplier")
public class SupplierController {
	@Autowired
	private SupplierService supplierService;
	
	@RequestMapping("/page")
	public ModelAndView page(HttpServletRequest request,IndexVo vo){
	    ModelMap model = new ModelMap();
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(user!=null){
			model.addAttribute("userInfo", user);
		}
	    return new ModelAndView("supplier", model);
	}
	
	@RequestMapping("/findAll")
	@ResponseBody
    public Map<String,Object> findAll(HttpServletRequest request,IndexVo vo){
		Map<String,Object> map = new HashMap<String,Object>();
		List<Supplier> list = supplierService.findAll(vo);
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", list.size());
		map.put("data", list);
        return map;
    }
	
	@RequestMapping("/del")
	@ResponseBody
    public Map<String,Object> del(HttpServletRequest request,IndexVo vo){
		Map<String,Object> map = supplierService.del(vo);
        return map;
    }
	
	@RequestMapping("/add")
	@ResponseBody
    public Map<String,Object> add(HttpServletRequest request,Supplier vo){
		Map<String,Object> map = new HashMap<String,Object>();
		if(vo.getId()==null){
			map = supplierService.add(vo);
		}else{
			map = supplierService.edit(vo);
		}
        return map;
    }
}
