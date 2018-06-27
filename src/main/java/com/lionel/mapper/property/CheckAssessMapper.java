package com.lionel.mapper.property;

import com.lionel.model.property.CheckAssess;
import java.util.List;

public interface CheckAssessMapper {
    int deleteByPrimaryKey(String assessId);

    int insert(CheckAssess record);

    CheckAssess selectByPrimaryKey(String assessId);

    List<CheckAssess> selectAll();

    int updateByPrimaryKey(CheckAssess record);
}