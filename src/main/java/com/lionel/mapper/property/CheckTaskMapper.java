package com.lionel.mapper.property;

import com.lionel.model.property.CheckTask;
import java.util.List;

public interface CheckTaskMapper {
    int deleteByPrimaryKey(String taskId);

    int insert(CheckTask record);

    CheckTask selectByPrimaryKey(String taskId);

    List<CheckTask> selectAll();

    int updateByPrimaryKey(CheckTask record);
}