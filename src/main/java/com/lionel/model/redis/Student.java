package com.lionel.model.redis;

import java.io.Serializable;

public class Student implements Serializable{
	
	private int sid;
	private String sname;
	private int sage;
	private String sex;
	
	public Student() {}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public void setSage(int sage) {
		this.sage = sage;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getSid() {

		return sid;
	}

	public String getSname() {
		return sname;
	}

	public int getSage() {
		return sage;
	}

	public String getSex() {
		return sex;
	}

	public Student(int sid, String sname, int sage, String sex) {

		this.sid = sid;
		this.sname = sname;
		this.sage = sage;
		this.sex = sex;
	}
}
