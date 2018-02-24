package cn.wwj.service;

import java.util.List;
import java.util.Map;

import cn.wwj.po.Menu;
import cn.wwj.po.UserInfo;
import cn.wwj.vo.IndexVo;

public interface UserService {

	Map<String, Object> login(IndexVo vo);

	UserInfo getUserById(Integer id);

	List<Menu> getMenu(Integer roleId);

	Integer editUser(IndexVo vo);

	List<UserInfo> findAll(IndexVo vo);

	Map<String, Object> del(IndexVo vo);

	Map<String, Object> add(IndexVo vo);

}
