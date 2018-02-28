package cn.wwj.service;

import java.util.List;
import java.util.Map;

import cn.wwj.po.Offer;
import cn.wwj.vo.IndexVo;

public interface OfferService {
	List<Offer> findAll(IndexVo vo);

	Map<String, Object> del(IndexVo vo);

	Map<String, Object> add(IndexVo vo);

	Map<String, Object> edit(Offer vo);

	Map<String, String> getES(IndexVo vo);

	Integer addStatistical(IndexVo vo);

	Integer editMoney(Offer vo);
}
