package com.lionel.mapper.property;

import com.lionel.model.property.Repair;
import java.util.List;

public interface RepairMapper {
    int deleteByPrimaryKey(String repairId);

    int insert(Repair record);

    Repair selectByPrimaryKey(String repairId);

    List<Repair> selectAll();

    int updateByPrimaryKey(Repair record);
}