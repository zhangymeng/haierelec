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

import cn.wwj.po.Offer;
import cn.wwj.po.UserInfo;
import cn.wwj.service.OfferService;
import cn.wwj.vo.IndexVo;

@Controller
@RequestMapping("offer")
public class OfferController {
	@Autowired
	private OfferService offerService;
	
	@RequestMapping("/page")
	public ModelAndView page(HttpServletRequest request,IndexVo vo){
	    ModelMap model = new ModelMap();
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(user!=null){
			model.addAttribute("userInfo", user);
		}
	    return new ModelAndView("offer", model);
	}
	
	@RequestMapping("/findAll")
	@ResponseBody
    public Map<String,Object> findAll(HttpServletRequest request,IndexVo vo){
		Map<String,Object> map = new HashMap<String,Object>();
		//用户索引
		if(vo.getTitle()!=null || vo.getMaxMoney()>0 || vo.getMinMoney()>0){
			UserInfo user = (UserInfo)request.getSession().getAttribute("user");
			if(user!=null){
				vo.setUserId(user.getId());
				vo.setUsername(user.getUsername());
			}
		}
		
		List<Offer> list = offerService.findAll(vo);
		map.put("code", 0);
		map.put("msg", "");
		map.put("count", list.size());
		map.put("data", list);
        return map;
    }
	
	@RequestMapping("/del")
	@ResponseBody
    public Map<String,Object> del(HttpServletRequest request,IndexVo vo){
		Map<String,Object> map = offerService.del(vo);
        return map;
    }
	
	@RequestMapping("/add")
	@ResponseBody
    public Map<String,Object> add(HttpServletRequest request,IndexVo vo){
		Map<String,Object> map = new HashMap<String,Object>();
		if(vo.getId()==null){
			map = offerService.add(vo);
		}else{
			Offer offer = new Offer();
			offer.setId(vo.getId());
			offer.setMoney(vo.getMoney());
			offer.setUrl(vo.getUrl());
			map = offerService.edit(offer);
		}
        return map;
    }
	
	@RequestMapping("/getES")
	@ResponseBody
	public Map<String,String> getES(IndexVo vo){
		Map<String,String> map = offerService.getES(vo);
		return map;
	}
	
	@RequestMapping("/uPage")
	public ModelAndView uPage(HttpServletRequest request,IndexVo vo){
	    ModelMap model = new ModelMap();
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(user!=null){
			model.addAttribute("userInfo", user);
		}
	    return new ModelAndView("userOffer", model);
	}
	
	
	@RequestMapping("/statisticalPage")
	public ModelAndView statisticalPage(HttpServletRequest request,IndexVo vo){
	    ModelMap model = new ModelMap();
	    model.addAttribute("offerId", vo.getOfferId());
	    return new ModelAndView("statistical", model);
	}
	

	
}
