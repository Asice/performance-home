package com.qurich.external.model;

import java.util.Date;

public class Equity {

	private int id;
	private String stock;
	private String name;
	private String type;
	private int scount;
	private float cost;
	private float cost_end;
	private float market;
	private float cost_per;
	private float jzbjdyk;
	private String yjyx;
	private Date pday;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getScount() {
		return scount;
	}
	public void setScount(int scount) {
		this.scount = scount;
	}
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	public float getCost_end() {
		return cost_end;
	}
	public void setCost_end(float cost_end) {
		this.cost_end = cost_end;
	}
	public float getMarket() {
		return market;
	}
	public void setMarket(float market) {
		this.market = market;
	}
	public float getCost_per() {
		return cost_per;
	}
	public void setCost_per(float cost_per) {
		this.cost_per = cost_per;
	}
	public float getJzbjdyk() {
		return jzbjdyk;
	}
	public void setJzbjdyk(float jzbjdyk) {
		this.jzbjdyk = jzbjdyk;
	}
	public String getYjyx() {
		return yjyx;
	}
	public void setYjyx(String yjyx) {
		this.yjyx = yjyx;
	}
	public Date getPday() {
		return pday;
	}
	public void setPday(Date pday) {
		this.pday = pday;
	}
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	
}
