package com.lionel.mapper.property;

import com.lionel.model.property.CheckStaff;
import java.util.List;

public interface CheckStaffMapper {
    int insert(CheckStaff record);

    List<CheckStaff> selectAll();
}