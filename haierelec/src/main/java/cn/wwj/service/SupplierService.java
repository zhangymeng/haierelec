package cn.wwj.service;

import java.util.List;
import java.util.Map;

import cn.wwj.po.Supplier;
import cn.wwj.vo.IndexVo;

public interface SupplierService {
	List<Supplier> findAll(IndexVo vo);

	Map<String, Object> del(IndexVo vo);

	Map<String, Object> add(Supplier vo);

	Map<String, Object> edit(Supplier vo);
}
