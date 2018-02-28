package cn.wwj.po;

import java.sql.Timestamp;

import cn.wwj.util.Tools;

public class Statistical {
	private Integer id;
	private Integer offerId;
	private double money;
	private String createDate;
	private Offer offer;
	
	public Offer getOffer() {
		return offer;
	}
	public void setOffer(Offer offer) {
		this.offer = offer;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOfferId() {
		return offerId;
	}
	public void setOfferId(Integer offerId) {
		this.offerId = offerId;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = Tools.foarmatDateTime(createDate);
	}
	
	
}
