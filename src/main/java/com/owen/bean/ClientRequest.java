package com.owen.bean;

import java.io.Serializable;

public class ClientRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer sex=1;
	private String name="";
	private Integer year=1996;
	private Integer month=2;
	private Integer day=1;
	private Integer time=9;
	private Integer lyear=1996;
	private Integer lmonth=1;
	private Integer lday=1;
	private Integer ltime=1;
	private Integer usegod=1;
	private Integer lovegod=2;
	private Integer hategod=3;
	
	public ClientRequest mock() {
		this.sex = 1;
		name = "test";
		year = 1996;
		month = 2;
		day = 1;
		time = 9;
		usegod = 1;
		lovegod = 1;
		hategod = 1;
		return this;
	}
	
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getLyear() {
		return lyear;
	}
	public void setLyear(int lyear) {
		this.lyear = lyear;
	}
	public int getLmonth() {
		return lmonth;
	}
	public void setLmonth(int lmonth) {
		this.lmonth = lmonth;
	}
	public int getLday() {
		return lday;
	}
	public void setLday(int lday) {
		this.lday = lday;
	}
	public int getLtime() {
		return ltime;
	}
	public void setLtime(int ltime) {
		this.ltime = ltime;
	}
	public int getUsegod() {
		return usegod;
	}
	public void setUsegod(int usegod) {
		this.usegod = usegod;
	}
	public int getLovegod() {
		return lovegod;
	}
	public void setLovegod(int lovegod) {
		this.lovegod = lovegod;
	}
	public int getHategod() {
		return hategod;
	}
	public void setHategod(int hategod) {
		this.hategod = hategod;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public void setLyear(Integer lyear) {
		this.lyear = lyear;
	}

	public void setLmonth(Integer lmonth) {
		this.lmonth = lmonth;
	}

	public void setLday(Integer lday) {
		this.lday = lday;
	}

	public void setLtime(Integer ltime) {
		this.ltime = ltime;
	}

	public void setUsegod(Integer usegod) {
		this.usegod = usegod;
	}

	public void setLovegod(Integer lovegod) {
		this.lovegod = lovegod;
	}

	public void setHategod(Integer hategod) {
		this.hategod = hategod;
	}

	@Override
	public String toString() {
		return "ClientRequest [sex=" + sex + ", name=" + name + ", year=" + year + ", month=" + month + ", day=" + day
				+ ", time=" + time + ", lyear=" + lyear + ", lmonth=" + lmonth + ", lday=" + lday + ", ltime=" + ltime
				+ ", usegod=" + usegod + ", lovegod=" + lovegod + ", hategod=" + hategod + "]";
	}
	

}
