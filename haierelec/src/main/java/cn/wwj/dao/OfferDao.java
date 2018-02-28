package cn.wwj.dao;

import java.util.List;

import cn.wwj.po.Elec;
import cn.wwj.po.Offer;
import cn.wwj.vo.IndexVo;

public interface OfferDao {
	List<Offer> findAll(IndexVo vo);

	Integer del(IndexVo vo);

	Integer add(Offer vo);

	Integer edit(Offer vo);

	Offer getById(Integer id);
}
