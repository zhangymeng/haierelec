package cn.wwj.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wwj.dao.ElecDao;
import cn.wwj.po.Elec;
import cn.wwj.util.Tools;
import cn.wwj.vo.IndexVo;

@Service
public class ElecServiceImpl implements ElecService {
	@Autowired
	private ElecDao elecDao;
	
	@Override
	public List<Elec> findAll(IndexVo vo) {
		List<Elec> list = elecDao.findAll(vo);
		return list;
	}

	@Override
	public Map<String, Object> del(IndexVo vo) {
		boolean result = false;
		String reason = "";
		Integer count = elecDao.del(vo);
		if(count>0){
			result = true;
		}
		return Tools.resultMap(result, reason);
	}

	@Override
	public Map<String, Object> add(Elec vo) {
		boolean result = false;
		String reason = "";
		//重复
		IndexVo inVo = new IndexVo();
		inVo.setElecNo(vo.getElecNo());
		List<Elec> list = elecDao.findAll(inVo);
		if(list.size()>0){
			reason = "编号已存在";
		}else{
			elecDao.add(vo);
			result = true;
		}
			
		return Tools.resultMap(result, reason);
	}

	
	@Override
	public Map<String, Object> edit(Elec vo) {
		boolean result = false;
		String reason = "";
		//正在使用不能修改
		//重复
		IndexVo inVo = new IndexVo();
		inVo.setElecNo(vo.getElecNo());
		List<Elec> list = elecDao.findAll(inVo);
		if(list.size()>0 && list.get(0).getId()!=vo.getId()){
			reason = "编号已存在";
		}else{
			elecDao.edit(vo);
			result = true;
		}
		return Tools.resultMap(result, reason);
	}
}
