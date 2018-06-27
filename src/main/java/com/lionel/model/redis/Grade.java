package com.lionel.model.redis;

public class Grade {
	
	private int cid;
	private int sid;
	private int grade;
	
	public Grade() {}
	public Grade(int cid, int sid, int grade) {
		super();
		this.cid = cid;
		this.sid = sid;
		this.grade = grade;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getSid() {
		return sid;
	}
	public void setSid(int sid) {
		this.sid = sid;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}

}
