package com.lionel.model.property;

import java.util.Date;

public class CheckTask {
    private String taskId;

    private Integer taskType;

    private String mallId;

    private String propertyId;

    private String staffId;

    private Integer pointType;

    private String pointId;

    private String pointName;

    private String pointLocation;

    private Date checkDate;

    private Integer checkTimePlan;

    private Integer checkTimeActual;

    private Date updateTime;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public String getmallId() {
        return mallId;
    }

    public void setmallId(String mallId) {
        this.mallId = mallId == null ? null : mallId.trim();
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId == null ? null : propertyId.trim();
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId == null ? null : staffId.trim();
    }

    public Integer getPointType() {
        return pointType;
    }

    public void setPointType(Integer pointType) {
        this.pointType = pointType;
    }

    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId == null ? null : pointId.trim();
    }

    public String getPointName() {
        return pointName;
    }

    public void setPointName(String pointName) {
        this.pointName = pointName == null ? null : pointName.trim();
    }

    public String getPointLocation() {
        return pointLocation;
    }

    public void setPointLocation(String pointLocation) {
        this.pointLocation = pointLocation == null ? null : pointLocation.trim();
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
    }

    public Integer getCheckTimePlan() {
        return checkTimePlan;
    }

    public void setCheckTimePlan(Integer checkTimePlan) {
        this.checkTimePlan = checkTimePlan;
    }

    public Integer getCheckTimeActual() {
        return checkTimeActual;
    }

    public void setCheckTimeActual(Integer checkTimeActual) {
        this.checkTimeActual = checkTimeActual;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}