package cn.wwj.vo;

import java.sql.Timestamp;

import cn.wwj.util.Tools;

public class IndexVo {
	private Integer id;
	
	private String username;
	private String password;
	private Timestamp createDate;
	private String passwords;
	private String oldPassword;
	
	private String elecNo;
	private String supplierNo;
	private Integer eId;
	private Integer sId;
	
	private Integer roleId;
	
	private double money;
	private String url;
	
	private String title;
	private double minMoney;
	private double maxMoney;
	private Integer userId;
	
	private Integer offerId;
	
	private String number;
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Integer getOfferId() {
		return offerId;
	}
	public void setOfferId(Integer offerId) {
		this.offerId = offerId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
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
		this.minMoney = Tools.formatDouble(minMoney);
	}
	public double getMaxMoney() {
		return maxMoney;
	}
	public void setMaxMoney(double maxMoney) {
		this.maxMoney = Tools.formatDouble(maxMoney);
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = Tools.formatDouble(money);
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer geteId() {
		return eId;
	}
	public void seteId(Integer eId) {
		this.eId = eId;
	}
	public Integer getsId() {
		return sId;
	}
	public void setsId(Integer sId) {
		this.sId = sId;
	}
	public String getSupplierNo() {
		return supplierNo;
	}
	public void setSupplierNo(String supplierNo) {
		this.supplierNo = supplierNo;
	}
	public String getElecNo() {
		return elecNo;
	}
	public void setElecNo(String elecNo) {
		this.elecNo = elecNo;
	}
	public String getPasswords() {
		return passwords;
	}
	public void setPasswords(String passwords) {
		this.passwords = passwords;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
	
	
}
