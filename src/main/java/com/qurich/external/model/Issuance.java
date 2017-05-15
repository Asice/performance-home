package com.qurich.external.model;

import java.util.Date;

/**
 * 定向增发
 * @author Asice
 *
 */
public class Issuance {

	private int id;
	private String stock;
	private String name;
	private float zfsl;
	private float zgb;
	private float price;
	private float nprice; //最新价
	private Date pday;
	private Date ipday;
	private String lockt;
	private Date udate;
	private float zfzgb;
	private float zyjl;
	private float mjze;
	
	public float getMjze() {
		return mjze;
	}
	public void setMjze(float mjze) {
		this.mjze = mjze;
	}
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
	public Date getPday() {
		return pday;
	}
	public void setPday(Date pday) {
		this.pday = pday;
	}
	public Date getIpday() {
		return ipday;
	}
	public void setIpday(Date ipday) {
		this.ipday = ipday;
	}
	public String getLockt() {
		return lockt;
	}
	public void setLockt(String lockt) {
		this.lockt = lockt;
	}
	public Date getUdate() {
		return udate;
	}
	public void setUdate(Date udate) {
		this.udate = udate;
	}
	
}
