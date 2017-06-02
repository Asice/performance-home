package com.qurich.external.model;

import java.util.Date;

public class EquityStock {

	private int id;
	private String stock;
	private String name;
	private String type;
	private String category;
	private String cgsl;
	private String cgbl;
	private String zhyk;
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getCgsl() {
		return cgsl;
	}
	public void setCgsl(String cgsl) {
		this.cgsl = cgsl;
	}
	public String getCgbl() {
		return cgbl;
	}
	public void setCgbl(String cgbl) {
		this.cgbl = cgbl;
	}
	public String getZhyk() {
		return zhyk;
	}
	public void setZhyk(String zhyk) {
		this.zhyk = zhyk;
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
