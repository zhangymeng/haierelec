package cn.wwj.dao;

import java.util.List;

import cn.wwj.po.Statistical;
import cn.wwj.vo.IndexVo;

public interface StatisticalDao {

	Integer add(IndexVo vo);

	Integer del(IndexVo vo);

	List<Statistical> findAll(IndexVo vo);

	List<String> findDate(IndexVo vo);

}
