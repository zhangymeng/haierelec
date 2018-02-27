package cn.wwj.dao;

import java.util.List;

import cn.wwj.po.LogInfo;
import cn.wwj.vo.IndexVo;

public interface LogDao {
	
	List<LogInfo> findAll(IndexVo vo);
	
	Integer add(IndexVo vo);
	
	Integer del(IndexVo vo);
}
