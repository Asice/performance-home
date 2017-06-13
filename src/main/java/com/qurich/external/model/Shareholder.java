package com.qurich.external.model;

import java.util.Date;

public class Shareholder {

	private int id;
	private String stock;
	private String name;
	private String holder;
	private int type;
	private String stype;
	private String atype;
	private int rank;
	private float mun;
	private float per;
	private float ltsz;
	private float bdsum;
	private float bdbl;
	private String bdtype;
	private Date rday;
	private Date pday;
	private Date ctime;
	
	public float getBdsum() {
		return bdsum;
	}
	public void setBdsum(float bdsum) {
		this.bdsum = bdsum;
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
	public String getHolder() {
		return holder;
	}
	public void setHolder(String holder) {
		this.holder = holder;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getStype() {
		return stype;
	}
	public void setStype(String stype) {
		this.stype = stype;
	}
	public String getAtype() {
		return atype;
	}
	public void setAtype(String atype) {
		this.atype = atype;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public float getMun() {
		return mun;
	}
	public void setMun(float mun) {
		this.mun = mun;
	}
	public float getPer() {
		return per;
	}
	public void setPer(float per) {
		this.per = per;
	}
	public float getLtsz() {
		return ltsz;
	}
	public void setLtsz(float ltsz) {
		this.ltsz = ltsz;
	}
	public float getBdbl() {
		return bdbl;
	}
	public void setBdbl(float bdbl) {
		this.bdbl = bdbl;
	}
	public String getBdtype() {
		return bdtype;
	}
	public void setBdtype(String bdtype) {
		this.bdtype = bdtype;
	}
	public Date getRday() {
		return rday;
	}
	public void setRday(Date rday) {
		this.rday = rday;
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
