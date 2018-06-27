package com.lionel.model.property;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Condition {
	
	private String mallId;
	
	private String propertyId;

	private Date startTime;

	private Date endTime;
	
	private Integer pageNum;
	
	private Integer pageSize;
	
	public Condition() {}
	
	

	public Date getStartTime() {
		return startTime;
	}
	
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}
	
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getMallId() {
		return mallId;
	}

	public void setMallId(String mallId) {
		this.mallId = mallId;
	}

	public String getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(String propertyId) {
		this.propertyId = propertyId;
	}

	public Integer getPageNum() {
		if(pageNum == null)
			return 1;
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		if(pageSize ==null)
			return 10;
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	
	

}
