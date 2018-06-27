package com.lionel.model.property;

import java.util.Date;

public class CheckAssess {
    private String assessId;

    private String mallId;

    private String propertyId;

    private Date assessTime;

    private String year;

    private Integer month;

    private Integer totalDays;

    private Integer assessNumber;

    private Integer qualified;

    private Integer disqualified;

    public String getAssessId() {
        return assessId;
    }

    public void setAssessId(String assessId) {
        this.assessId = assessId == null ? null : assessId.trim();
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

    public Date getAssessTime() {
        return assessTime;
    }

    public void setAssessTime(Date assessTime) {
        this.assessTime = assessTime;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getTotalDays() {
        return totalDays;
    }

    public void setTotalDays(Integer totalDays) {
        this.totalDays = totalDays;
    }

    public Integer getAssessNumber() {
        return assessNumber;
    }

    public void setAssessNumber(Integer assessNumber) {
        this.assessNumber = assessNumber;
    }

    public Integer getQualified() {
        return qualified;
    }

    public void setQualified(Integer qualified) {
        this.qualified = qualified;
    }

    public Integer getDisqualified() {
        return disqualified;
    }

    public void setDisqualified(Integer disqualified) {
        this.disqualified = disqualified;
    }
}