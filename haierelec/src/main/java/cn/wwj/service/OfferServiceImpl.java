package cn.wwj.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;

import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wwj.controller.GetYinInfo;
import cn.wwj.dao.ElecDao;
import cn.wwj.dao.LogDao;
import cn.wwj.dao.OfferDao;
import cn.wwj.dao.StatisticalDao;
import cn.wwj.dao.SupplierDao;
import cn.wwj.po.Elec;
import cn.wwj.po.Offer;
import cn.wwj.po.Statistical;
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
	
	@Autowired
	private StatisticalDao statisticalDao;
	
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
			o.setsUrlStr(s.getUrl());
			
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
			vo.setOfferId(vo.getId());
			statisticalDao.del(vo);
		}
		return Tools.resultMap(result, reason);
	}

	@Override
	public Map<String, Object> add(IndexVo vo) {
		boolean result = false;
		String reason = "";
		Offer offer = new Offer();
		offer.setNumber(vo.getNumber());
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
					//查询报价
					String sUrl = slist.get(0).getUrl();
					String urlStr = sUrl.replace("ELECNO", vo.getNumber());
					
					try {
						String json = GetYinInfo.httpGet(urlStr, "utf-8");
						double money = GetYinInfo.getMoneyByJson(json,slist.get(0).getId());
						offer.setMoney(money);
						
						offerDao.add(offer);
						result = true;
						
					} catch (HttpException e) {
						// TODO Auto-generated catch block
						System.out.println(slist.get(0).getTitle()+"--"+"获取报价失败");
						reason = "获取报价失败";
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						System.out.println(slist.get(0).getTitle()+"--"+"获取报价失败");
						reason = "获取报价失败";
						e.printStackTrace();
					}
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
		Offer o = offerDao.getById(vo.getId());
		Supplier s = supplierDao.getById(o.getsId());
		
		String sUrl = s.getUrl();
		String urlStr = sUrl.replace("ELECNO", vo.getNumber());
		
		try {
			String json = GetYinInfo.httpGet(urlStr, "utf-8");
			double money = GetYinInfo.getMoneyByJson(json,s.getId());
			vo.setMoney(money);
			
			offerDao.edit(vo);
			result = true;
			
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			System.out.println(s.getTitle()+"--"+"获取报价失败");
			reason = "获取报价失败";
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(s.getTitle()+"--"+"获取报价失败");
			reason = "获取报价失败";
			e.printStackTrace();
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

	@Override
	public Integer addStatistical(IndexVo vo) {
		Integer count = statisticalDao.add(vo);
		return count;
	}

	@Override
	public Integer editMoney(Offer vo) {
		Integer count = offerDao.edit(vo);
		return count;
	}

	@Override
	public String allStatistical(IndexVo vo) {
		
	    List<Map<String,Object>> listRes = new ArrayList<Map<String,Object>>();
		List<Statistical> list = statisticalDao.findAll(vo);
		List<String> dateList = statisticalDao.findDate(vo);
		
		for(String s : dateList){
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("createDate", s.substring(0,s.length()-2) );
			for(Statistical sta :list){
				if(sta.getCreateDate().equals(s.substring(0,s.length()-2))){
					if(sta.getOffer().getsId()==1){
						//京东
						map.put("jingdong", sta.getMoney());
					}else if(sta.getOffer().getsId()==2){
						//国美
						map.put("guomei", sta.getMoney());
					}else if(sta.getOffer().getsId()==3){
						//苏宁
						map.put("suning", sta.getMoney());
					}
				}
			}
			listRes.add(map);
		}
		return JSONArray.fromObject(listRes).toString();
	}
	
	
}
