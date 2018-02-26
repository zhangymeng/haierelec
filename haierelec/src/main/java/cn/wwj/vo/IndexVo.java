package cn.wwj.vo;

import java.sql.Timestamp;

public class IndexVo {
	private Integer id;
	
	private String username;
	private String password;
	private Timestamp createDate;
	private String passwords;
	private String oldPassword;
	
	private String elecNo;
	
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
