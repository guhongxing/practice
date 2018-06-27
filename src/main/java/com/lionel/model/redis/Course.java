package com.lionel.model.redis;

public class Course {
	
	private String cname;
	private String tname;
	
	public Course() {}
	
	public Course(String cname, String tname) {
		super();
		this.cname = cname;
		this.tname = tname;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getTname() {
		return tname;
	}
	public void setTname(String tname) {
		this.tname = tname;
	}

}
