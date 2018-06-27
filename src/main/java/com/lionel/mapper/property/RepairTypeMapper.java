package com.lionel.mapper.property;

import com.lionel.model.property.RepairType;
import java.util.List;

public interface RepairTypeMapper {
    int insert(RepairType record);

    List<RepairType> selectAll();
}