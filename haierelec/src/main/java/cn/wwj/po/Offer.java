package cn.wwj.po;

public class Offer {
	private Integer id;
	private Integer eId;
	private Integer sId;
	private double money;
	private String number;
	private String url;
	
	private Elec elec;
	
	private String eTitle;
	private String eNo;
	private String sTitle;
	private String sNo;
	private String sUrlStr;
	
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getsUrlStr() {
		return sUrlStr;
	}
	public void setsUrlStr(String sUrlStr) {
		this.sUrlStr = sUrlStr;
	}
	public Elec getElec() {
		return elec;
	}
	public void setElec(Elec elec) {
		this.elec = elec;
	}
	public String geteTitle() {
		return eTitle;
	}
	public void seteTitle(String eTitle) {
		this.eTitle = eTitle;
	}
	public String geteNo() {
		return eNo;
	}
	public void seteNo(String eNo) {
		this.eNo = eNo;
	}
	public String getsTitle() {
		return sTitle;
	}
	public void setsTitle(String sTitle) {
		this.sTitle = sTitle;
	}
	public String getsNo() {
		return sNo;
	}
	public void setsNo(String sNo) {
		this.sNo = sNo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
}
