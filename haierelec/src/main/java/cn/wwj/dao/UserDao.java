package cn.wwj.dao;

import java.util.List;

import cn.wwj.po.UserInfo;
import cn.wwj.vo.IndexVo;

public interface UserDao {

	UserInfo getUserByUsername(IndexVo vo);
	
	UserInfo getUserById(Integer id);

	Integer edit(IndexVo vo);

	List<UserInfo> findAll(IndexVo vo);

	Integer del(IndexVo vo);

	Integer add(IndexVo vo);

}
