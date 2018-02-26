package cn.wwj.dao;

import java.util.List;

import cn.wwj.po.Elec;
import cn.wwj.po.Supplier;
import cn.wwj.vo.IndexVo;

public interface SupplierDao {
	List<Supplier> findAll(IndexVo vo);

	Integer del(IndexVo vo);

	Integer add(Supplier vo);

	Integer edit(Supplier vo);

	Supplier getById(Integer id);
}
