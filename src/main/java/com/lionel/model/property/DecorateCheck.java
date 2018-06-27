package com.lionel.model.property;

import java.util.Date;

public class DecorateCheck {
    private String id;

    private String decorateId;

    private String mallId;

    private String stallName;

    private Integer oldRate;

    private Integer newRate;

    private String recordId;

    private String recordName;

    private Date recordTime;

    private String recordExplain;

    private Integer picturesCount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getDecorateId() {
        return decorateId;
    }

    public void setDecorateId(String decorateId) {
        this.decorateId = decorateId == null ? null : decorateId.trim();
    }

    public String getmallId() {
        return mallId;
    }

    public void setmallId(String mallId) {
        this.mallId = mallId == null ? null : mallId.trim();
    }

    public String getStallName() {
        return stallName;
    }

    public void setStallName(String stallName) {
        this.stallName = stallName == null ? null : stallName.trim();
    }

    public Integer getOldRate() {
        return oldRate;
    }

    public void setOldRate(Integer oldRate) {
        this.oldRate = oldRate;
    }

    public Integer getNewRate() {
        return newRate;
    }

    public void setNewRate(Integer newRate) {
        this.newRate = newRate;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId == null ? null : recordId.trim();
    }

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName == null ? null : recordName.trim();
    }

    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }

    public String getRecordExplain() {
        return recordExplain;
    }

    public void setRecordExplain(String recordExplain) {
        this.recordExplain = recordExplain == null ? null : recordExplain.trim();
    }

    public Integer getPicturesCount() {
        return picturesCount;
    }

    public void setPicturesCount(Integer picturesCount) {
        this.picturesCount = picturesCount;
    }
}