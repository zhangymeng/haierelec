package cn.wwj.po;

import java.sql.Timestamp;

import cn.wwj.util.Tools;

public class LogInfo {
	
	private Integer id;
	private String title;
	private double minMoney;
	private double maxMoney;
	private Integer userId;
	private String username;
	private String createDate;
	private UserInfo user;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	private String userType;
	
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public UserInfo getUser() {
		return user;
	}
	public void setUser(UserInfo user) {
		this.user = user;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getMinMoney() {
		return minMoney;
	}
	public void setMinMoney(double minMoney) {
		this.minMoney = minMoney;
	}
	public double getMaxMoney() {
		return maxMoney;
	}
	public void setMaxMoney(double maxMoney) {
		this.maxMoney = maxMoney;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = Tools.foarmatDateTime(createDate);
	}
	
}
