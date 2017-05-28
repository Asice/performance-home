package com.qurich.external.model;

import java.util.Date;

/**
 * 概念
 * @author Asice
 *
 */
public class Concept {

	private int id;
	private String name;
	private Date ctime;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
}
