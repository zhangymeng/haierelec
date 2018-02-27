package cn.wwj.service;

import java.util.List;
import java.util.Map;

import cn.wwj.po.Elec;
import cn.wwj.po.LogInfo;
import cn.wwj.vo.IndexVo;

public interface LogService {

	List<LogInfo> findAll(IndexVo vo);

	Map<String, Object> del(IndexVo vo);

}
