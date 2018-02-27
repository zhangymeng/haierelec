package cn.wwj.service;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wwj.dao.ElecDao;
import cn.wwj.dao.LogDao;
import cn.wwj.dao.OfferDao;
import cn.wwj.dao.SupplierDao;
import cn.wwj.po.Elec;
import cn.wwj.po.Offer;
import cn.wwj.po.Supplier;
import cn.wwj.util.Tools;
import cn.wwj.vo.IndexVo;

@Service
public class OfferServiceImpl implements OfferService {
	
	@Autowired
	private OfferDao offerDao;
	
	@Autowired
	private ElecDao elecDao;
	
	@Autowired
	private SupplierDao supplierDao;
	
	@Autowired
	private LogDao logDao;
	
	@Override
	public List<Offer> findAll(IndexVo vo) {
		//索引的数据加入log
		if(vo.getTitle()!=null || vo.getMaxMoney()>0 || vo.getMinMoney()>0){
			if(vo.getTitle().equals("")){
				vo.setTitle(null);
			}
			Timestamp createDate = new Timestamp(System.currentTimeMillis());//当前时间
			vo.setCreateDate(createDate);
			logDao.add(vo);
		}
		
		List<Offer> list = offerDao.findAll(vo);
		for(Offer o:list){
			Supplier s = supplierDao.getById(o.getsId());
			o.setsTitle(s.getTitle());
			o.setsNo(s.getSupplierNo());
			o.seteTitle(o.getElec().getTitle());
			o.seteNo(o.getElec().getElecNo());
		}
		return list;
	}

	@Override
	public Map<String, Object> del(IndexVo vo) {
		boolean result = false;
		String reason = "";
		Integer count = offerDao.del(vo);
		if(count>0){
			result = true;
		}
		return Tools.resultMap(result, reason);
	}

	@Override
	public Map<String, Object> add(IndexVo vo) {
		boolean result = false;
		String reason = "";
		Offer offer = new Offer();
		offer.setMoney(vo.getMoney());
		offer.setUrl(vo.getUrl());
		List<Elec> elist = elecDao.findAll(vo);
		if(elist.size()>0){
			vo.seteId(elist.get(0).getId());
			offer.seteId(elist.get(0).getId());
			List<Supplier> slist = supplierDao.findAll(vo);
			if(slist.size()>0){
				vo.setsId(slist.get(0).getId());
				offer.setsId(slist.get(0).getId());
				//重复
				List<Offer> list = offerDao.findAll(vo);
				if(list.size()>0){
					reason = "该报价已存在";
				}else{
					offerDao.add(offer);
					result = true;
				}
			}else{
				reason = "供应商编号不存在";
			}
		}else{
			reason = "电器编号不存在";
		}
			
		return Tools.resultMap(result, reason);
	}

	
	@Override
	public Map<String, Object> edit(Offer vo) {
		boolean result = false;
		String reason = "";
		//正在使用不能修改
		//重复
		IndexVo inVo = new IndexVo();
		inVo.seteId(vo.geteId());
		inVo.setsId(vo.getsId());
		List<Offer> list = offerDao.findAll(inVo);
		if(list.size()>0 && list.get(0).getId()!=vo.getId()){
			reason = "该报价已存在";
		}else{
			offerDao.edit(vo);
			result = true;
		}
		return Tools.resultMap(result, reason);
	}

	@Override
	public Map<String, String> getES(IndexVo vo) {
		Map<String, String> map = new HashMap<String,String>();
		List<Elec> elist = elecDao.findAll(vo);
		if(elist.size()>0){
			map.put("eTitle", elist.get(0).getTitle());
			List<Supplier> slist = supplierDao.findAll(vo);
			if(slist.size()>0){
				map.put("sTitle", slist.get(0).getTitle());
			}else{
				map.put("res", "供应商编号不存在");
				map.put("sTitle", "");
			}
		}else{
			map.put("res", "电器编号不存在");
			map.put("eTitle", "");
		}
		return map;
	}
}
