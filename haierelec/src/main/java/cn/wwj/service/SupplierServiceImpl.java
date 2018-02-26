package cn.wwj.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wwj.dao.OfferDao;
import cn.wwj.dao.SupplierDao;
import cn.wwj.po.Supplier;
import cn.wwj.util.Tools;
import cn.wwj.vo.IndexVo;

@Service
public class SupplierServiceImpl implements SupplierService {
	@Autowired
	private SupplierDao supplierDao;
	
	@Autowired
	private OfferDao offerDao;
	
	@Override
	public List<Supplier> findAll(IndexVo vo) {
		List<Supplier> list = supplierDao.findAll(vo);
		return list;
	}

	@Override
	public Map<String, Object> del(IndexVo vo) {
		boolean result = false;
		String reason = "";
		Integer count = supplierDao.del(vo);
		if(count>0){
			//删除报价表
			IndexVo inVo = new IndexVo();
			inVo.setsId(vo.getId());
			offerDao.del(inVo);
			result = true;
		}
		return Tools.resultMap(result, reason);
	}

	@Override
	public Map<String, Object> add(Supplier vo) {
		boolean result = false;
		String reason = "";
		//重复
		IndexVo inVo = new IndexVo();
		inVo.setSupplierNo(vo.getSupplierNo());
		List<Supplier> list = supplierDao.findAll(inVo);
		if(list.size()>0){
			reason = "编号已存在";
		}else{
			supplierDao.add(vo);
			result = true;
		}
			
		return Tools.resultMap(result, reason);
	}

	
	@Override
	public Map<String, Object> edit(Supplier vo) {
		boolean result = false;
		String reason = "";
		//正在使用不能修改
		//重复
		IndexVo inVo = new IndexVo();
		inVo.setSupplierNo(vo.getSupplierNo());
		List<Supplier> list = supplierDao.findAll(inVo);
		if(list.size()>0 && list.get(0).getId()!=vo.getId()){
			reason = "编号已存在";
		}else{
			supplierDao.edit(vo);
			result = true;
		}
		return Tools.resultMap(result, reason);
	}
}
