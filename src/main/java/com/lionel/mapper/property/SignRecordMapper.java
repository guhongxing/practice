package com.lionel.mapper.property;

import com.lionel.model.property.SignRecord;
import java.util.List;

public interface SignRecordMapper {
    int deleteByPrimaryKey(String signId);

    int insert(SignRecord record);

    SignRecord selectByPrimaryKey(String signId);

    List<SignRecord> selectAll();

    int updateByPrimaryKey(SignRecord record);
}