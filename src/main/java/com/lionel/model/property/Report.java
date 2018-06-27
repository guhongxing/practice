package com.lionel.model.property;

import java.util.Date;

public class Report {
    private String reportId;

    private Integer reportType;

    private String mallId;

    private String reportEquipment;

    private String equipmentId;

    private String reportMessage;

    private String location;

    private Integer picturesCount;

    private String reporterId;

    private String reporterPhone;

    private String reporterName;

    private Date reporterTime;

    private Integer status;

    private String solverId;

    private String solverName;

    private Date solveTime;

    private String solveExplain;

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId == null ? null : reportId.trim();
    }

    public Integer getReportType() {
        return reportType;
    }

    public void setReportType(Integer reportType) {
        this.reportType = reportType;
    }

    public String getmallId() {
        return mallId;
    }

    public void setmallId(String mallId) {
        this.mallId = mallId == null ? null : mallId.trim();
    }

    public String getReportEquipment() {
        return reportEquipment;
    }

    public void setReportEquipment(String reportEquipment) {
        this.reportEquipment = reportEquipment == null ? null : reportEquipment.trim();
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId == null ? null : equipmentId.trim();
    }

    public String getReportMessage() {
        return reportMessage;
    }

    public void setReportMessage(String reportMessage) {
        this.reportMessage = reportMessage == null ? null : reportMessage.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public Integer getPicturesCount() {
        return picturesCount;
    }

    public void setPicturesCount(Integer picturesCount) {
        this.picturesCount = picturesCount;
    }

    public String getReporterId() {
        return reporterId;
    }

    public void setReporterId(String reporterId) {
        this.reporterId = reporterId == null ? null : reporterId.trim();
    }

    public String getReporterPhone() {
        return reporterPhone;
    }

    public void setReporterPhone(String reporterPhone) {
        this.reporterPhone = reporterPhone == null ? null : reporterPhone.trim();
    }

    public String getReporterName() {
        return reporterName;
    }

    public void setReporterName(String reporterName) {
        this.reporterName = reporterName == null ? null : reporterName.trim();
    }

    public Date getReporterTime() {
        return reporterTime;
    }

    public void setReporterTime(Date reporterTime) {
        this.reporterTime = reporterTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSolverId() {
        return solverId;
    }

    public void setSolverId(String solverId) {
        this.solverId = solverId == null ? null : solverId.trim();
    }

    public String getSolverName() {
        return solverName;
    }

    public void setSolverName(String solverName) {
        this.solverName = solverName == null ? null : solverName.trim();
    }

    public Date getSolveTime() {
        return solveTime;
    }

    public void setSolveTime(Date solveTime) {
        this.solveTime = solveTime;
    }

    public String getSolveExplain() {
        return solveExplain;
    }

    public void setSolveExplain(String solveExplain) {
        this.solveExplain = solveExplain == null ? null : solveExplain.trim();
    }
}