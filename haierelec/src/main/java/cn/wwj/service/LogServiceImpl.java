package cn.wwj.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.wwj.dao.LogDao;
import cn.wwj.dao.UserDao;
import cn.wwj.po.LogInfo;
import cn.wwj.po.UserInfo;
import cn.wwj.util.Tools;
import cn.wwj.vo.IndexVo;

@Service
public class LogServiceImpl implements LogService {

	@Autowired
	private LogDao logDao;
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public List<LogInfo> findAll(IndexVo vo) {
		List<LogInfo> list = logDao.findAll(vo);
		for(LogInfo l:list){
			UserInfo ui = userDao.getUserById(l.getUserId());
			if(ui!=null){
				l.setUserType("正常");
			}else{
				l.setUserType("用户已删除");
			}
			if(l.getTitle()==null || l.getTitle().equals("")){
				l.setTitle("未搜索");
			}
		}
		return list;
	}

	@Override
	public Map<String, Object> del(IndexVo vo) {
		logDao.del(vo);
		return Tools.resultMap(true, "");
	}

}
