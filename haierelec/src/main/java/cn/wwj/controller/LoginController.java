package cn.wwj.controller;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cn.wwj.po.Menu;
import cn.wwj.po.UserInfo;
import cn.wwj.service.UserService;
import cn.wwj.vo.IndexVo;

@Controller
@RequestMapping("login")
public class LoginController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 登录 username password
	 * @param vo
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
    public Map<String,Object> login(IndexVo vo,HttpServletRequest request){
		 Map<String,Object> map = userService.login(vo);
		 
		 boolean result = (Boolean) map.get("result");
		 String idStr = (String) map.get("reason");
		 
		 if(result){
			 Integer id = Integer.parseInt(idStr);
			 UserInfo user = userService.getUserById(id);
			 request.getSession().setAttribute("user", user);
		 }
		 
		 return map;
    }
	
	
	@RequestMapping("/regist")
    public ModelAndView regist(HttpServletRequest request){
		ModelMap model = new ModelMap();
        return new ModelAndView("regist", model);
    }
	
	/**
	 * 登录后主页面
	 * @param vo
	 * @param request
	 * @return
	 */
	@RequestMapping("/index")
    public ModelAndView index(HttpServletRequest request){
		String urlStr = "index";
		ModelMap model = new ModelMap();
		UserInfo user = (UserInfo)request.getSession().getAttribute("user");
		if(user!=null){
			urlStr = "homePage";
			model.addAttribute("userInfo", user);
			//菜单
			List<Menu> menuList = userService.getMenu(user.getRoleId());
			model.addAttribute("menuList", menuList);
		}
        
        return new ModelAndView(urlStr, model);
    }
	
	/**
	 * 注销
	 * @param request
	 * @return
	 */
	@RequestMapping("/logout")
    public ModelAndView logout(HttpServletRequest request){
		ModelMap model = new ModelMap();
		Enumeration<?> em = request.getSession().getAttributeNames();
		  while(em.hasMoreElements()){
		   request.getSession().removeAttribute(em.nextElement().toString());
		}
        return new ModelAndView("index", model);
    }
	
	
	@RequestMapping("/basic")
    public ModelAndView basic(HttpServletRequest request){
		ModelMap model = new ModelMap();
        return new ModelAndView("basic", model);
    }

}
