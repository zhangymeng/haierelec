package cn.wwj.dao;

import java.util.List;

import cn.wwj.po.Elec;
import cn.wwj.vo.IndexVo;

public interface ElecDao {
	List<Elec> findAll(IndexVo vo);

	Integer del(IndexVo vo);

	Integer add(Elec vo);

	Integer edit(Elec vo);

	Elec getById(Integer id);
}
