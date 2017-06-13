package com.qurich.external.model;

import java.util.Date;

public class ShareholderPer {

	private int id;
	private String stock;
	private String name;
	private float ltsz;
	private float fltsz;
	private float tltsz;
	private float per;
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
	public float getLtsz() {
		return ltsz;
	}
	public void setLtsz(float ltsz) {
		this.ltsz = ltsz;
	}
	public float getFltsz() {
		return fltsz;
	}
	public void setFltsz(float fltsz) {
		this.fltsz = fltsz;
	}
	public float getTltsz() {
		return tltsz;
	}
	public void setTltsz(float tltsz) {
		this.tltsz = tltsz;
	}
	public float getPer() {
		return per;
	}
	public void setPer(float per) {
		this.per = per;
	}
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	
	
}
