package com.qurich.external.model;

import java.util.Date;

/**
 * 定向增发
 * @author Asice
 *
 */
public class IssuanceNotice {

	private int id;
	private String stock;
	private String name;
	private String schedule;
	private float zfsl;
	private float zgb;
	private float yamjje;
	private float price;
	private float nprice; //最新价
	private Date dshday;
	private Date gddhday;
	private Date fswday;
	private Date zjhshday;
	private Date fxxgday;
	private Date xgssday;
	private Date udate;
	private float zfzgb;
	private float zyjl;
	
	public float getZfzgb() {
		return zfzgb;
	}
	public void setZfzgb(float zfzgb) {
		this.zfzgb = zfzgb;
	}
	public float getZyjl() {
		return zyjl;
	}
	public void setZyjl(float zyjl) {
		this.zyjl = zyjl;
	}
	public float getYamjje() {
		return yamjje;
	}
	public void setYamjje(float yamjje) {
		this.yamjje = yamjje;
	}
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
	public String getSchedule() {
		return schedule;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	public float getZfsl() {
		return zfsl;
	}
	public void setZfsl(float zfsl) {
		this.zfsl = zfsl;
	}
	public float getZgb() {
		return zgb;
	}
	public void setZgb(float zgb) {
		this.zgb = zgb;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getNprice() {
		return nprice;
	}
	public void setNprice(float nprice) {
		this.nprice = nprice;
	}
	public Date getDshday() {
		return dshday;
	}
	public void setDshday(Date dshday) {
		this.dshday = dshday;
	}
	public Date getGddhday() {
		return gddhday;
	}
	public void setGddhday(Date gddhday) {
		this.gddhday = gddhday;
	}
	public Date getFswday() {
		return fswday;
	}
	public void setFswday(Date fswday) {
		this.fswday = fswday;
	}
	public Date getZjhshday() {
		return zjhshday;
	}
	public void setZjhshday(Date zjhshday) {
		this.zjhshday = zjhshday;
	}
	public Date getFxxgday() {
		return fxxgday;
	}
	public void setFxxgday(Date fxxgday) {
		this.fxxgday = fxxgday;
	}
	public Date getXgssday() {
		return xgssday;
	}
	public void setXgssday(Date xgssday) {
		this.xgssday = xgssday;
	}
	public Date getUdate() {
		return udate;
	}
	public void setUdate(Date udate) {
		this.udate = udate;
	}
	
}
