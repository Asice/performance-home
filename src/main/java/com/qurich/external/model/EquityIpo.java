package com.qurich.external.model;

import java.util.Date;

public class EquityIpo {

	private int id;
	private String stock;
	private String name;
	private float market;
	private String ipo_name;
	private float percent;
	private float cost;
	private float price;
	private String shjd;
	private String dxhy;
	private Date ctime;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getMarket() {
		return market;
	}
	public void setMarket(float market) {
		this.market = market;
	}
	public String getIpo_name() {
		return ipo_name;
	}
	public void setIpo_name(String ipo_name) {
		this.ipo_name = ipo_name;
	}
	public float getPercent() {
		return percent;
	}
	public void setPercent(float percent) {
		this.percent = percent;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getShjd() {
		return shjd;
	}
	public void setShjd(String shjd) {
		this.shjd = shjd;
	}
	public String getDxhy() {
		return dxhy;
	}
	public void setDxhy(String dxhy) {
		this.dxhy = dxhy;
	}
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	
}
