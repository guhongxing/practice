package com.lionel.model.property;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Point implements Serializable{
    private String pointId;

    private String mallId;
    
    private String mallName;

    private String serialNumber;

    private String pointName;

    private Integer pointType;

    private String pointLocation;

    private String registerName;

    private Date registerTime;

    private String updateId;

    private String updateName;

    private Date updateTime;
    
    public Point() {
    	
    }
    
    public Point(String mallId, String serialNumber, String pointName,
			String pointLocation, String registerName) {
		super();
		this.mallId = mallId;
		this.serialNumber = serialNumber;
		this.pointName = pointName;
		this.pointLocation = pointLocation;
		this.registerName = registerName;
	}

	public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId == null ? null : pointId.trim();
    }

    public String getmallId() {
        return mallId;
    }

    public void setmallId(String mallId) {
        this.mallId = mallId == null ? null : mallId.trim();
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber == null ? null : serialNumber.trim();
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName == null ? null : pointName.trim();
    }

    public Integer getPointType() {
        return pointType;
    }

    public void setPointType(Integer pointType) {
        this.pointType = pointType;
    }

    public String getPointLocation() {
        return pointLocation;
    }

    public void setPointLocation(String pointLocation) {
        this.pointLocation = pointLocation == null ? null : pointLocation.trim();
    }

    public String getRegisterName() {
        return registerName;
    }

    public void setRegisterName(String registerName) {
        this.registerName = registerName == null ? null : registerName.trim();
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId == null ? null : updateId.trim();
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName == null ? null : updateName.trim();
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public String getmallName() {
		return mallName;
	}

	public void setmallName(String mallName) {
		this.mallName = mallName;
	}
    
}