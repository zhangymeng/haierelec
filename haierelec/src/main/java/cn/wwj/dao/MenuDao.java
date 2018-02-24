package cn.wwj.dao;

import java.util.List;

import cn.wwj.po.Menu;

public interface MenuDao {

	List<Menu> findAll(List<Integer> list);

}
