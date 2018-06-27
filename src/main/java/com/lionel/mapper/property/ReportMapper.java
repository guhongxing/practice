package com.lionel.mapper.property;

import com.lionel.model.property.Report;
import java.util.List;

public interface ReportMapper {
    int deleteByPrimaryKey(String reportId);

    int insert(Report record);

    Report selectByPrimaryKey(String reportId);

    List<Report> selectAll();

    int updateByPrimaryKey(Report record);
}