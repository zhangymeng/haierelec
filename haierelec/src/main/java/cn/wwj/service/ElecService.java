package cn.wwj.service;

import java.util.List;
import java.util.Map;

import cn.wwj.po.Elec;
import cn.wwj.vo.IndexVo;

public interface ElecService {
	List<Elec> findAll(IndexVo vo);

	Map<String, Object> del(IndexVo vo);

	Map<String, Object> add(Elec vo);

	Map<String, Object> edit(Elec vo);
}
